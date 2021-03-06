package org.lunifera.examples.ecview.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@SuppressWarnings("all")
public class Address implements Serializable {
  private boolean disposed;
  
  @Basic
  private String street;
  
  @Basic
  private String city;
  
  @Basic
  private String country;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Transient
  public boolean isDisposed() {
    return this.disposed;
  }
  
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
    disposed = true;
  }
  
  /**
   * Returns the street property or <code>null</code> if not present.
   */
  public String getStreet() {
    checkDisposed();
    return this.street;
  }
  
  /**
   * Sets the street property to this instance.
   */
  public void setStreet(final String street) {
    checkDisposed();
    this.street = street;
  }
  
  /**
   * Returns the city property or <code>null</code> if not present.
   */
  public String getCity() {
    checkDisposed();
    return this.city;
  }
  
  /**
   * Sets the city property to this instance.
   */
  public void setCity(final String city) {
    checkDisposed();
    this.city = city;
  }
  
  /**
   * Returns the country property or <code>null</code> if not present.
   */
  public String getCountry() {
    checkDisposed();
    return this.country;
  }
  
  /**
   * Sets the country property to this instance.
   */
  public void setCountry(final String country) {
    checkDisposed();
    this.country = country;
  }
}
