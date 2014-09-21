package org.lunifera.examples.ecview;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@SuppressWarnings("all")
public class ButtonCallback implements Serializable {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  private boolean disposed;
  
  private long previousPerson;
  
  private long nextPerson;
  
  private boolean hasNext;
  
  private boolean hasPrevious;
  
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
   * Returns the previousPerson property or <code>null</code> if not present.
   */
  public long getPreviousPerson() {
    checkDisposed();
    return this.previousPerson;
  }
  
  /**
   * Sets the <code>previousPerson</code> property to this instance.
   * 
   * @param previousPerson - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPreviousPerson(final long previousPerson) {
    firePropertyChange("previousPerson", this.previousPerson, this.previousPerson = previousPerson );
  }
  
  /**
   * Returns the nextPerson property or <code>null</code> if not present.
   */
  public long getNextPerson() {
    checkDisposed();
    return this.nextPerson;
  }
  
  /**
   * Sets the <code>nextPerson</code> property to this instance.
   * 
   * @param nextPerson - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setNextPerson(final long nextPerson) {
    firePropertyChange("nextPerson", this.nextPerson, this.nextPerson = nextPerson );
  }
  
  /**
   * Returns the hasNext property or <code>null</code> if not present.
   */
  public boolean isHasNext() {
    checkDisposed();
    return this.hasNext;
  }
  
  /**
   * Sets the <code>hasNext</code> property to this instance.
   * 
   * @param hasNext - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setHasNext(final boolean hasNext) {
    firePropertyChange("hasNext", this.hasNext, this.hasNext = hasNext );
  }
  
  /**
   * Returns the hasPrevious property or <code>null</code> if not present.
   */
  public boolean isHasPrevious() {
    checkDisposed();
    return this.hasPrevious;
  }
  
  /**
   * Sets the <code>hasPrevious</code> property to this instance.
   * 
   * @param hasPrevious - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setHasPrevious(final boolean hasPrevious) {
    firePropertyChange("hasPrevious", this.hasPrevious, this.hasPrevious = hasPrevious );
  }
}
