package org.lunifera.examples.ecview.model.ui

import java.util.Date
import java.util.Random
import javax.persistence.EntityManagerFactory
import org.lunifera.examples.ecview.model.jpa.Address
import org.lunifera.examples.ecview.model.jpa.Company
import org.lunifera.examples.ecview.model.jpa.Gender
import org.lunifera.examples.ecview.model.jpa.Person

class DbSetup {

	def setupDB(EntityManagerFactory emf) {
		val em = emf.createEntityManager
		em.transaction.begin
		
		val firstname = #['Florian', 'John', 'Klemens', 'Jennifer', 'Sabrina']
		val lastname = #['Pirchner', 'Johnnson', 'Miller', 'Mayer', 'Edler']
		val mails = #['john@example.com', 'florian@example.com', 'klemens@example.com', 'bar@example.com',
			'foo@example.com']
		val gender = #[Gender.FEMALE, Gender.MALE]
		val married = #[true, false]
		val streetAddress = #['On the beach 7', 'At the montain 9', 'Behind the lake 17', 'In the middle 78',
			'Near the sun 12']
		val city = #['Munich', 'San Francisco', 'Vienna', 'Moon',
			'Jupiter']
		val country = #['Austria', 'Germany', 'US', 'France',
			'Spain']
		val texts = #['Hey you', 'Hey there', 'Whats up', 'Are you fine?', 'Come on...']
		val company = #['Lunifera', 'Compex Commerce', 'Bakery', 'Coffe house', 'Millers fishery']
		val age = #[24, 36, 42, 54, 62]
		val images = #["ad.gif", "at.gif", "au.gif", "ba.gif", "bg.gif", "bn.gif", "bs.gif", "bv.gif", "ca.gif",
			"cd.gif", "ch.gif", "ci.gif", "ck.gif", "cn.gif", "de.gif"]

		val Random rnd = new Random(5)
		for (i : 0 .. 500) {
			val d = new Person
			d.firstname = firstname.get(rnd.nextInt(5))
			d.lastname = lastname.get(rnd.nextInt(5))
			d.age = age.get(rnd.nextInt(5))
			d.mailaddress = mails.get(rnd.nextInt(5))
			d.gender = gender.get(rnd.nextInt(2))
			d.married = married.get(rnd.nextInt(2))
			d.birthday = new Date()
			d.imagePath = "icons/flags/" + images.get(rnd.nextInt(15))
			
			val Address address = new Address
			address.street = streetAddress.get(rnd.nextInt(5))
			address.city = city.get(rnd.nextInt(5))
			address.country = streetAddress.get(rnd.nextInt(5))
			d.address = address
			
			em.persist(d)

			val Company h = new Company
			h.name = company.get(rnd.nextInt(5))
			h.address = streetAddress.get(rnd.nextInt(5))
			em.persist(h)
		}
		
		em.transaction.commit
	}
	
}
