package org.lunifera.examples.ecview;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.lunifera.examples.ecview.Address;

@SuppressWarnings("all")
public class Person implements Serializable {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  private boolean disposed;
  
  private String firstname;
  
  private String lastname;
  
  private Address homeAddress;
  
  private Address companyAddress;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  public boolean isDisposed() {
    return this.disposed;
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
   */
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
   */
  public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
   */
  public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
   */
  public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
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
   * Returns the homeAddress property or <code>null</code> if not present.
   */
  public Address getHomeAddress() {
    checkDisposed();
    return this.homeAddress;
  }
  
  /**
   * Sets the <code>homeAddress</code> property to this instance.
   * 
   * @param homeAddress - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setHomeAddress(final Address homeAddress) {
    checkDisposed();
    firePropertyChange("homeAddress", this.homeAddress, this.homeAddress = homeAddress);
  }
  
  /**
   * Returns the companyAddress property or <code>null</code> if not present.
   */
  public Address getCompanyAddress() {
    checkDisposed();
    return this.companyAddress;
  }
  
  /**
   * Sets the <code>companyAddress</code> property to this instance.
   * 
   * @param companyAddress - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCompanyAddress(final Address companyAddress) {
    checkDisposed();
    firePropertyChange("companyAddress", this.companyAddress, this.companyAddress = companyAddress);
  }
  
  public void ecviewInitialize() {
    this.firstname = "Florian";
    this.lastname = "Pirchner";
    final Address homeAddress = new Address();
    homeAddress.setStreet("Stephansplace 1");
    homeAddress.setCity("Vienna");
    homeAddress.setPostalcode("1010");
    this.homeAddress = homeAddress;
    final Address companyAddress = new Address();
    companyAddress.setStreet("Prater");
    companyAddress.setCity("Vienna");
    companyAddress.setPostalcode("1220");
    this.companyAddress = companyAddress;
  }
}
