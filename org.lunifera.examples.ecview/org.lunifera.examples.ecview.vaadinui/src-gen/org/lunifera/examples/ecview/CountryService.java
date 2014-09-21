package org.lunifera.examples.ecview;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.examples.ecview.Country;

@SuppressWarnings("all")
public class CountryService implements Serializable {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  private boolean disposed;
  
  private List<Country> countries;
  
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
   * Returns an unmodifiable list of countries.
   */
  public List<Country> getCountries() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetCountries());
  }
  
  /**
   * Returns the list of <code>Country</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<Country> internalGetCountries() {
    if (this.countries == null) {
      this.countries = new java.util.ArrayList<Country>();
    }
    return this.countries;
  }
  
  /**
   * Adds the given country to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>Country#parent</code> of the <code>country</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link Country#setParent(Country)}.
   * 
   * @param country - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToCountries(final Country country) {
    checkDisposed();
    country.setParent(this);
  }
  
  /**
   * Removes the given country from this object. <p>
   * 
   * @param country - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromCountries(final Country country) {
    checkDisposed();
    country.setParent(null);
  }
  
  /**
   * For internal use only!
   */
  void internalAddToCountries(final Country country) {
    internalGetCountries().add(country);
  }
  
  /**
   * For internal use only!
   */
  void internalRemoveFromCountries(final Country country) {
    internalGetCountries().remove(country);
  }
  
  public void ecviewInitialize() {
    final Country germany = new Country();
    germany.setName("Germany");
    germany.setIsoCode("DE");
    germany.setImagePath("icons/flags/de.gif");
    this.addToCountries(germany);
    final Country austria = new Country();
    austria.setName("Austria");
    austria.setIsoCode("AT");
    austria.setImagePath("icons/flags/at.gif");
    this.addToCountries(austria);
  }
  
  public void initService() {
    final Country germany = new Country();
    germany.setName("Germany");
    germany.setIsoCode("DE");
    germany.setImagePath("icons/flags/de.gif");
    this.addToCountries(germany);
    final Country austria = new Country();
    austria.setName("Austria");
    austria.setIsoCode("AT");
    austria.setImagePath("icons/flags/at.gif");
    this.addToCountries(austria);
    final Country brazil = new Country();
    brazil.setName("Brazli");
    brazil.setIsoCode("BR");
    brazil.setImagePath("icons/flags/br.gif");
    this.addToCountries(brazil);
  }
}
