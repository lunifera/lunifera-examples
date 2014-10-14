package org.lunifera.examples.ecview.model.jpa;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lunifera.examples.ecview.model.jpa.Address;
import org.lunifera.examples.ecview.model.jpa.Base;
import org.lunifera.examples.ecview.model.jpa.Company;
import org.lunifera.examples.ecview.model.jpa.Gender;

@Entity
@Table(name = "PERSON")
@SuppressWarnings("all")
public class Person extends Base {
  @Column(name = "FIRSTNAME")
  private String firstname;
  
  @Column(name = "LASTNAME")
  private String lastname;
  
  @Column(name = "MAILADDRESS")
  private String mailaddress;
  
  @Column(name = "BIRTHDAY")
  @Temporal(value = TemporalType.DATE)
  private Date birthday;
  
  @Column(name = "AGE")
  private int age;
  
  @Column(name = "MARRIED")
  private boolean married;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")), @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")), @AttributeOverride(name = "country", column = @Column(name = "ADDRESS_COUNTRY")) })
  @Column(name = "ADDRESS")
  private Address address;
  
  @Column(name = "GENDER")
  private Gender gender;
  
  @Column(name = "IMAGE_PATH")
  private String imagePath;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EMPLOYER")
  private Company employer;
  
  /**
   * Checks whether the object is disposed.
   * @throws RuntimeException if the object is disposed.
   */
  private void checkDisposed() {
    if (isDisposed()) {
      throw new RuntimeException("Object already disposed: " + this);
    }
  }
  
  /**
   * Calling dispose will destroy that instance. The internal state will be 
   * set to 'disposed' and methods of that object must not be used anymore. 
   * Each call will result in runtime exceptions.<br/>
   * If this object keeps composition containments, these will be disposed too. 
   * So the whole composition containment tree will be disposed on calling this method.
   */
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    super.dispose();
  }
  
  /**
   * Returns the firstname property or <code>null</code> if not present.
   */
  public String getFirstname() {
    checkDisposed();
    return this.firstname;
  }
  
  /**
   * Sets the firstname property to this instance.
   */
  public void setFirstname(final String firstname) {
    checkDisposed();
    this.firstname = firstname;
  }
  
  /**
   * Returns the lastname property or <code>null</code> if not present.
   */
  public String getLastname() {
    checkDisposed();
    return this.lastname;
  }
  
  /**
   * Sets the lastname property to this instance.
   */
  public void setLastname(final String lastname) {
    checkDisposed();
    this.lastname = lastname;
  }
  
  /**
   * Returns the mailaddress property or <code>null</code> if not present.
   */
  public String getMailaddress() {
    checkDisposed();
    return this.mailaddress;
  }
  
  /**
   * Sets the mailaddress property to this instance.
   */
  public void setMailaddress(final String mailaddress) {
    checkDisposed();
    this.mailaddress = mailaddress;
  }
  
  /**
   * Returns the birthday property or <code>null</code> if not present.
   */
  public Date getBirthday() {
    checkDisposed();
    return this.birthday;
  }
  
  /**
   * Sets the birthday property to this instance.
   */
  public void setBirthday(final Date birthday) {
    checkDisposed();
    this.birthday = birthday;
  }
  
  /**
   * Returns the age property or <code>null</code> if not present.
   */
  public int getAge() {
    checkDisposed();
    return this.age;
  }
  
  /**
   * Sets the age property to this instance.
   */
  public void setAge(final int age) {
    checkDisposed();
    this.age = age;
  }
  
  /**
   * Returns the married property or <code>null</code> if not present.
   */
  public boolean isMarried() {
    checkDisposed();
    return this.married;
  }
  
  /**
   * Sets the married property to this instance.
   */
  public void setMarried(final boolean married) {
    checkDisposed();
    this.married = married;
  }
  
  /**
   * Returns the address property or <code>null</code> if not present.
   */
  public Address getAddress() {
    checkDisposed();
    return this.address;
  }
  
  /**
   * Sets the address property to this instance.
   */
  public void setAddress(final Address address) {
    checkDisposed();
    this.address = address;
  }
  
  /**
   * Returns the gender property or <code>null</code> if not present.
   */
  public Gender getGender() {
    checkDisposed();
    return this.gender;
  }
  
  /**
   * Sets the gender property to this instance.
   */
  public void setGender(final Gender gender) {
    checkDisposed();
    this.gender = gender;
  }
  
  /**
   * Returns the imagePath property or <code>null</code> if not present.
   */
  public String getImagePath() {
    checkDisposed();
    return this.imagePath;
  }
  
  /**
   * Sets the imagePath property to this instance.
   */
  public void setImagePath(final String imagePath) {
    checkDisposed();
    this.imagePath = imagePath;
  }
  
  /**
   * Returns the employer property or <code>null</code> if not present.
   */
  public Company getEmployer() {
    checkDisposed();
    return this.employer;
  }
  
  /**
   * Sets the employer property to this instance.
   */
  public void setEmployer(final Company employer) {
    checkDisposed();
    this.employer = employer;
  }
}
