package org.lunifera.examples.ecview.model.jpa.dtos;

import java.io.Serializable;
import java.util.Date;

import org.lunifera.examples.ecview.model.jpa.Gender;
import org.lunifera.examples.ecview.model.jpa.dtos.AddressDto;
import org.lunifera.examples.ecview.model.jpa.dtos.BaseDto;
import org.lunifera.examples.ecview.model.jpa.dtos.CompanyDto;
import org.lunifera.runtime.common.annotations.DomainEmbedded;
import org.lunifera.runtime.common.annotations.DomainReference;

@SuppressWarnings("all")
public class PersonDto extends BaseDto implements Serializable {
  private String firstname;
  
  private String lastname;
  
  private String mailaddress;
  
  private Date birthday;
  
  private int age;
  
  private boolean married;
  
  @DomainEmbedded
  private AddressDto address;
  
  private Gender gender;
  
  private String imagePath;

  @DomainReference
  private CompanyDto employer;

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
   * Sets the <code>firstname</code> property to this instance.
   * 
   * @param firstname - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setFirstname(final String firstname) {
    firePropertyChange("firstname", this.firstname, this.firstname = firstname );
  }
  
  /**
   * Returns the lastname property or <code>null</code> if not present.
   */
  public String getLastname() {
    checkDisposed();
    return this.lastname;
  }
  
  /**
   * Sets the <code>lastname</code> property to this instance.
   * 
   * @param lastname - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setLastname(final String lastname) {
    firePropertyChange("lastname", this.lastname, this.lastname = lastname );
  }
  
  /**
   * Returns the mailaddress property or <code>null</code> if not present.
   */
  public String getMailaddress() {
    checkDisposed();
    return this.mailaddress;
  }
  
  /**
   * Sets the <code>mailaddress</code> property to this instance.
   * 
   * @param mailaddress - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setMailaddress(final String mailaddress) {
    firePropertyChange("mailaddress", this.mailaddress, this.mailaddress = mailaddress );
  }
  
  /**
   * Returns the birthday property or <code>null</code> if not present.
   */
  public Date getBirthday() {
    checkDisposed();
    return this.birthday;
  }
  
  /**
   * Sets the <code>birthday</code> property to this instance.
   * 
   * @param birthday - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setBirthday(final Date birthday) {
    firePropertyChange("birthday", this.birthday, this.birthday = birthday );
  }
  
  /**
   * Returns the age property or <code>null</code> if not present.
   */
  public int getAge() {
    checkDisposed();
    return this.age;
  }
  
  /**
   * Sets the <code>age</code> property to this instance.
   * 
   * @param age - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAge(final int age) {
    firePropertyChange("age", this.age, this.age = age );
  }
  
  /**
   * Returns the married property or <code>null</code> if not present.
   */
  public boolean isMarried() {
    checkDisposed();
    return this.married;
  }
  
  /**
   * Sets the <code>married</code> property to this instance.
   * 
   * @param married - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setMarried(final boolean married) {
    firePropertyChange("married", this.married, this.married = married );
  }
  
  /**
   * Returns the address property or <code>null</code> if not present.
   */
  public AddressDto getAddress() {
    checkDisposed();
    return this.address;
  }
  
  /**
   * Sets the <code>address</code> property to this instance.
   * 
   * @param address - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAddress(final AddressDto address) {
    firePropertyChange("address", this.address, this.address = address );
  }
  
  /**
   * Returns the gender property or <code>null</code> if not present.
   */
  public Gender getGender() {
    checkDisposed();
    return this.gender;
  }
  
  /**
   * Sets the <code>gender</code> property to this instance.
   * 
   * @param gender - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setGender(final Gender gender) {
    firePropertyChange("gender", this.gender, this.gender = gender );
  }
  
  /**
   * Returns the imagePath property or <code>null</code> if not present.
   */
  public String getImagePath() {
    checkDisposed();
    return this.imagePath;
  }
  
  /**
   * Sets the <code>imagePath</code> property to this instance.
   * 
   * @param imagePath - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setImagePath(final String imagePath) {
    firePropertyChange("imagePath", this.imagePath, this.imagePath = imagePath );
  }
  
  /**
   * Returns the employer property or <code>null</code> if not present.
   */
  public CompanyDto getEmployer() {
    checkDisposed();
    return this.employer;
  }
  
  /**
   * Sets the <code>employer</code> property to this instance.
   * 
   * @param employer - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setEmployer(final CompanyDto employer) {
    checkDisposed();
    firePropertyChange("employer", this.employer, this.employer = employer);
  }
}
