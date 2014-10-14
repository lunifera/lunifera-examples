package org.lunifera.examples.ecview.model.ui;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.lunifera.examples.ecview.model.jpa.Address;
import org.lunifera.examples.ecview.model.jpa.Company;
import org.lunifera.examples.ecview.model.jpa.Gender;
import org.lunifera.examples.ecview.model.jpa.Person;

@SuppressWarnings("all")
public class DbSetup {
  public void setupDB(final EntityManagerFactory emf) {
    final EntityManager em = emf.createEntityManager();
    EntityTransaction _transaction = em.getTransaction();
    _transaction.begin();
    final List<String> firstname = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Florian", "John", "Klemens", "Jennifer", "Sabrina"));
    final List<String> lastname = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Pirchner", "Johnnson", "Miller", "Mayer", "Edler"));
    final List<String> mails = Collections.<String>unmodifiableList(Lists.<String>newArrayList("john@example.com", "florian@example.com", "klemens@example.com", "bar@example.com", "foo@example.com"));
    final List<Gender> gender = Collections.<Gender>unmodifiableList(Lists.<Gender>newArrayList(Gender.FEMALE, Gender.MALE));
    final List<Boolean> married = Collections.<Boolean>unmodifiableList(Lists.<Boolean>newArrayList(Boolean.valueOf(true), Boolean.valueOf(false)));
    final List<String> streetAddress = Collections.<String>unmodifiableList(Lists.<String>newArrayList("On the beach 7", "At the montain 9", "Behind the lake 17", "In the middle 78", "Near the sun 12"));
    final List<String> city = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Munich", "San Francisco", "Vienna", "Moon", "Jupiter"));
    final List<String> country = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Austria", "Germany", "US", "France", "Spain"));
    final List<String> texts = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Hey you", "Hey there", "Whats up", "Are you fine?", "Come on..."));
    final List<String> company = Collections.<String>unmodifiableList(Lists.<String>newArrayList("Lunifera", "Compex Commerce", "Bakery", "Coffe house", "Millers fishery"));
    final List<Integer> age = Collections.<Integer>unmodifiableList(Lists.<Integer>newArrayList(Integer.valueOf(24), Integer.valueOf(36), Integer.valueOf(42), Integer.valueOf(54), Integer.valueOf(62)));
    final List<String> images = Collections.<String>unmodifiableList(Lists.<String>newArrayList("ad.gif", "at.gif", "au.gif", "ba.gif", "bg.gif", "bn.gif", "bs.gif", "bv.gif", "ca.gif", "cd.gif", "ch.gif", "ci.gif", "ck.gif", "cn.gif", "de.gif"));
    final Random rnd = new Random(5);
    IntegerRange _upTo = new IntegerRange(0, 500);
    for (final Integer i : _upTo) {
      {
        final Person d = new Person();
        int _nextInt = rnd.nextInt(5);
        String _get = firstname.get(_nextInt);
        d.setFirstname(_get);
        int _nextInt_1 = rnd.nextInt(5);
        String _get_1 = lastname.get(_nextInt_1);
        d.setLastname(_get_1);
        int _nextInt_2 = rnd.nextInt(5);
        Integer _get_2 = age.get(_nextInt_2);
        d.setAge((_get_2).intValue());
        int _nextInt_3 = rnd.nextInt(5);
        String _get_3 = mails.get(_nextInt_3);
        d.setMailaddress(_get_3);
        int _nextInt_4 = rnd.nextInt(2);
        Gender _get_4 = gender.get(_nextInt_4);
        d.setGender(_get_4);
        int _nextInt_5 = rnd.nextInt(2);
        Boolean _get_5 = married.get(_nextInt_5);
        d.setMarried((_get_5).booleanValue());
        Date _date = new Date();
        d.setBirthday(_date);
        int _nextInt_6 = rnd.nextInt(15);
        String _get_6 = images.get(_nextInt_6);
        String _plus = ("icons/flags/" + _get_6);
        d.setImagePath(_plus);
        final Address address = new Address();
        int _nextInt_7 = rnd.nextInt(5);
        String _get_7 = streetAddress.get(_nextInt_7);
        address.setStreet(_get_7);
        int _nextInt_8 = rnd.nextInt(5);
        String _get_8 = city.get(_nextInt_8);
        address.setCity(_get_8);
        int _nextInt_9 = rnd.nextInt(5);
        String _get_9 = streetAddress.get(_nextInt_9);
        address.setCountry(_get_9);
        d.setAddress(address);
        em.persist(d);
        final Company h = new Company();
        int _nextInt_10 = rnd.nextInt(5);
        String _get_10 = company.get(_nextInt_10);
        h.setName(_get_10);
        int _nextInt_11 = rnd.nextInt(5);
        String _get_11 = streetAddress.get(_nextInt_11);
        h.setAddress(_get_11);
        em.persist(h);
      }
    }
    EntityTransaction _transaction_1 = em.getTransaction();
    _transaction_1.commit();
  }
}
