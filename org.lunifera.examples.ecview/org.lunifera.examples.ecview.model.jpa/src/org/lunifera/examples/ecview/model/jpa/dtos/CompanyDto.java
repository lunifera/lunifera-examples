package org.lunifera.examples.ecview.model.jpa.dtos;

import java.io.Serializable;
import org.lunifera.examples.ecview.model.jpa.dtos.BaseDto;
import org.lunifera.runtime.common.annotations.DomainKey;

@SuppressWarnings("all")
public class CompanyDto extends BaseDto implements Serializable {
  @DomainKey
  private String name;
  
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
   * Sets the <code>name</code> property to this instance.
   * 
   * @param name - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setName(final String name) {
    firePropertyChange("name", this.name, this.name = name );
  }
  
  /**
   * Returns the address property or <code>null</code> if not present.
   */
  public String getAddress() {
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
  public void setAddress(final String address) {
    firePropertyChange("address", this.address, this.address = address );
  }
}
