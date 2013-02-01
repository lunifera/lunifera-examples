/*******************************************************************************
 *   Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *        Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.examples.kwiee.erp.module.core.services.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.util.ByteSource;
import org.lunifera.examples.kwiee.erp.module.core.domain.Employee;
import org.lunifera.examples.kwiee.erp.module.core.domain.SystemUser;
import org.lunifera.examples.kwiee.erp.module.core.domain.Task;
import org.lunifera.examples.kwiee.erp.module.core.services.IAdministrationService;
import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponentWithEntityManager;
import org.osgi.service.component.ComponentContext;

public class AdministrationServiceComponent extends
		AbstractServiceComponentWithEntityManager implements
		IAdministrationService {

	private int calling;

	@Override
	protected void activate(ComponentContext context,
			Map<String, Object> properties) {
		super.activate(context, properties);

		String valueStr = null;
		Object value = properties.get("createTestData");
		if(value instanceof Boolean)
			valueStr = ((Boolean)value).toString();
		if (value instanceof String)
			valueStr = (String) value;
		if (valueStr.equalsIgnoreCase("true")) {
			createTestData();
		}
	}

	public List<Task> fetchWithFilter(Map<String, Object> queryFilter,
			Object[] sortPropertyIds, boolean[] sortStates, int startIndex,
			int fetchSize) {
		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();
		CriteriaQuery<Task> criteriaQuery = criteriaBuilder
				.createQuery(Task.class);
		// Root<Task> from = criteriaQuery.from(Task.class);

		// Predicate predicate = criteriaBuilder.ge(from.get("pint"), arg1);

		TypedQuery<Task> q = getEntityManager().createQuery(criteriaQuery);

		return q.getResultList();
	}

	public int fetchSizeWithFilter(Map<String, Object> queryFilter) {
		CriteriaBuilder criteriaBuilder = getEntityManager()
				.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = criteriaBuilder.createQuery(Long.class);
		cQuery.select(criteriaBuilder.count(cQuery.from(Task.class)));

		System.out.println(++calling);

		// Predicate iqPredicate =
		// criteriaBuilder.equal(brainJoin.<Integer>get("iq"), 170);

		// cQuery.where(/*your stuff*/);
		return getEntityManager().createQuery(cQuery).getSingleResult()
				.intValue();
	}

	public void save(List<Task> addedObjects, List<Task> modifiedObjects,
			List<Task> removedObjects) {
//		CriteriaBuilder criteriaBuilder = getEntityManager()
//				.getCriteriaBuilder();
	}

	/**
	 * Create test data
	 */
	protected void createTestData() {

		EntityTransaction transaction = getEntityManager().getTransaction();
		transaction.begin();

		SystemUser user1 = new SystemUser();
		user1.setName("dan");
		user1.setPassword(encodePassword("demo"));
		getEntityManager().persist(user1);

		SystemUser user2 = new SystemUser();
		user2.setName("admin");
		user2.setPassword(encodePassword("demo"));
		getEntityManager().persist(user2);

		SystemUser user3 = new SystemUser();
		user3.setName("cvgaviao");
		user3.setPassword(encodePassword("demo"));
		getEntityManager().persist(user3);

		Employee person1 = new Employee();
		person1.setFirstName("Dany");
		person1.setLastName("Hamburguer");
		person1.setUser(user1);
		getEntityManager().persist(person1);

		Employee person2 = new Employee();
		person2.setFirstName("Joseph");
		person2.setLastName("Cheese");
		getEntityManager().persist(person2);

		Employee person3 = new Employee();
		person3.setFirstName("Cristiano");
		person3.setLastName("Gavião");
		person3.setUser(user3);
		getEntityManager().persist(person3);

		for (int i = 0; i < 150; i++) {
			Task task = new Task();
			task.setSubject("task-" + Integer.toString(i));
			if (i % 2 == 0) {
				task.setReporter(person2);
				task.setAssignee(person1);
			} else if (i % 3 == 0) {
				task.setReporter(person1);
				task.setAssignee(person3);
			} else {
				task.setReporter(person3);
				task.setAssignee(person2);
			}

			getEntityManager().persist(task);
		}
		transaction.commit();
	}

	private String encodePassword(String password2Encode) {

		byte[] saltKey;
		int encryptedSecretLenght;
		int saltKeyLenght;

		BlowfishCipherService cipher = new BlowfishCipherService();

		// generate key with default 256 bits size
		saltKey = cipher.generateNewKey(256).getEncoded();
		saltKeyLenght = saltKey.length;

		// encrypt the secret
		ByteSource encryptedSecret = cipher.encrypt(
				CodecSupport.toBytes(password2Encode), saltKey);
		encryptedSecretLenght = encryptedSecret.getBytes().length;

		// create a destination array that is the size of the two arrays
		byte[] destination = new byte[saltKeyLenght + encryptedSecretLenght];

		// copy ciphertext into start of destination (from pos 0, copy
		// ciphertext.length bytes)
		System.arraycopy(encryptedSecret.getBytes(), 0, destination, 0,
				encryptedSecretLenght);

		// copy mac into end of destination (from pos ciphertext.length, copy
		// mac.length bytes)
		System.arraycopy(saltKey, 0, destination, encryptedSecretLenght,
				saltKeyLenght);

		return Base64.encodeToString(destination);

	}

	protected String decodePassword(String password2Decode) {

		byte[] saltKey;
		BlowfishCipherService cipher = new BlowfishCipherService();
		// generate key with default 256 bits size
		saltKey = cipher.generateNewKey(256).getEncoded();

		byte[] decoded = Base64.decode(password2Decode);
		InputStream cipherIn = new ByteArrayInputStream(decoded);
		ByteArrayOutputStream plainOut = new ByteArrayOutputStream();

		cipher.decrypt(cipherIn, plainOut, saltKey);
		byte[] decrypted = plainOut.toByteArray();
		return decrypted.toString();
	}

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}
}
