package org.lunifera.examples.ecview.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.examples.ecview.model.jpa.Base;
import org.lunifera.runtime.common.annotations.DomainKey;

@Entity
@Table(name = "COMPANY")
@SuppressWarnings("all")
public class Company extends Base {
  @DomainKey
  @Column(name = "NAME")
  private String name;
  
  @Column(name = "ADDRESS")
  private String address;
  
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
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
    checkDisposed();
    return this.name;
  }
  
  /**
   * Sets the name property to this instance.
   */
  public void setName(final String name) {
    checkDisposed();
    this.name = name;
  }
  
  /**
   * Returns the address property or <code>null</code> if not present.
   */
  public String getAddress() {
    checkDisposed();
    return this.address;
  }
  
  /**
   * Sets the address property to this instance.
   */
  public void setAddress(final String address) {
    checkDisposed();
    this.address = address;
  }
}
