/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.collect.result;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.Nullable;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

/**
 * Details of a single failed item in a failure.
 * <p>
 * When a {@link Failure} occurs, details are captured and stored in an instance of this class.
 * Details include the reason, message and stack trace.
 * <p>
 * Instances of {@code FailureItem} are public classes created via {@link Result}.
 */
@BeanDefinition(builderScope = "private")
public final class FailureItem
    implements ImmutableBean {

  /**
   * Stack traces can take up a lot of memory if a large number of failures are stored.
   * They are often duplicated many times so interning them can save a significant amount of memory.
   */
  private static final Interner<String> INTERNER = Interners.newWeakInterner();

  /**
   * The reason associated with the failure.
   */
  @PropertyDefinition(validate = "notNull")
  private final FailureReason reason;
  /**
   * The error message associated with the failure.
   */
  @PropertyDefinition(validate = "notEmpty")
  private final String message;
  /**
   * Stack trace where the failure occurred.
   * If the failure was caused by an {@code Exception} its stack trace is used, otherwise it's the
   * location where the failure was created.
   */
  @PropertyDefinition(validate = "notNull")
  private final String stackTrace;
  /**
   * The type of the exception that caused the failure, null if it wasn't caused by an exception.
   */
  @PropertyDefinition
  @Nullable
  private final Class<? extends Exception> causeType;

  @ImmutableConstructor
  private FailureItem(
      FailureReason reason,
      String message,
      String stackTrace,
      @Nullable Class<? extends Exception> causeType) {
    JodaBeanUtils.notNull(reason, "reason");
    JodaBeanUtils.notEmpty(message, "message");
    JodaBeanUtils.notNull(stackTrace, "stackTrace");
    this.reason = reason;
    this.message = message;
    this.stackTrace = INTERNER.intern(stackTrace);
    this.causeType = causeType;
  }

  /**
   * Creates an instance.
   * 
   * @param reason  the reason
   * @param message  the message, not empty
   * @param stackTrace  the stack trace
   * @param causeType  the cause type, may be null
   * @return the failure item
   */
  static FailureItem of(
      FailureReason reason, String message, String stackTrace, @Nullable Class<? extends Exception> causeType) {
    return new FailureItem(reason, message, stackTrace, causeType);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FailureItem}.
   * @return the meta-bean, not null
   */
  public static FailureItem.Meta meta() {
    return FailureItem.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FailureItem.Meta.INSTANCE);
  }

  @Override
  public FailureItem.Meta metaBean() {
    return FailureItem.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reason associated with the failure.
   * @return the value of the property, not null
   */
  public FailureReason getReason() {
    return reason;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the error message associated with the failure.
   * @return the value of the property, not empty
   */
  public String getMessage() {
    return message;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets stack trace where the failure occurred.
   * If the failure was caused by an {@code Exception} its stack trace is used, otherwise it's the
   * location where the failure was created.
   * @return the value of the property, not null
   */
  public String getStackTrace() {
    return stackTrace;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type of the exception that caused the failure, null if it wasn't caused by an exception.
   * @return the value of the property
   */
  public Class<? extends Exception> getCauseType() {
    return causeType;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FailureItem other = (FailureItem) obj;
      return JodaBeanUtils.equal(getReason(), other.getReason()) &&
          JodaBeanUtils.equal(getMessage(), other.getMessage()) &&
          JodaBeanUtils.equal(getStackTrace(), other.getStackTrace()) &&
          JodaBeanUtils.equal(getCauseType(), other.getCauseType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getReason());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMessage());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStackTrace());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCauseType());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("FailureItem{");
    buf.append("reason").append('=').append(getReason()).append(',').append(' ');
    buf.append("message").append('=').append(getMessage()).append(',').append(' ');
    buf.append("stackTrace").append('=').append(getStackTrace()).append(',').append(' ');
    buf.append("causeType").append('=').append(JodaBeanUtils.toString(getCauseType()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FailureItem}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code reason} property.
     */
    private final MetaProperty<FailureReason> reason = DirectMetaProperty.ofImmutable(
        this, "reason", FailureItem.class, FailureReason.class);
    /**
     * The meta-property for the {@code message} property.
     */
    private final MetaProperty<String> message = DirectMetaProperty.ofImmutable(
        this, "message", FailureItem.class, String.class);
    /**
     * The meta-property for the {@code stackTrace} property.
     */
    private final MetaProperty<String> stackTrace = DirectMetaProperty.ofImmutable(
        this, "stackTrace", FailureItem.class, String.class);
    /**
     * The meta-property for the {@code causeType} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<? extends Exception>> causeType = DirectMetaProperty.ofImmutable(
        this, "causeType", FailureItem.class, (Class) Class.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "reason",
        "message",
        "stackTrace",
        "causeType");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -934964668:  // reason
          return reason;
        case 954925063:  // message
          return message;
        case 2026279837:  // stackTrace
          return stackTrace;
        case -1443456189:  // causeType
          return causeType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FailureItem> builder() {
      return new FailureItem.Builder();
    }

    @Override
    public Class<? extends FailureItem> beanType() {
      return FailureItem.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code reason} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FailureReason> reason() {
      return reason;
    }

    /**
     * The meta-property for the {@code message} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> message() {
      return message;
    }

    /**
     * The meta-property for the {@code stackTrace} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> stackTrace() {
      return stackTrace;
    }

    /**
     * The meta-property for the {@code causeType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<? extends Exception>> causeType() {
      return causeType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -934964668:  // reason
          return ((FailureItem) bean).getReason();
        case 954925063:  // message
          return ((FailureItem) bean).getMessage();
        case 2026279837:  // stackTrace
          return ((FailureItem) bean).getStackTrace();
        case -1443456189:  // causeType
          return ((FailureItem) bean).getCauseType();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code FailureItem}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<FailureItem> {

    private FailureReason reason;
    private String message;
    private String stackTrace;
    private Class<? extends Exception> causeType;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -934964668:  // reason
          return reason;
        case 954925063:  // message
          return message;
        case 2026279837:  // stackTrace
          return stackTrace;
        case -1443456189:  // causeType
          return causeType;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -934964668:  // reason
          this.reason = (FailureReason) newValue;
          break;
        case 954925063:  // message
          this.message = (String) newValue;
          break;
        case 2026279837:  // stackTrace
          this.stackTrace = (String) newValue;
          break;
        case -1443456189:  // causeType
          this.causeType = (Class<? extends Exception>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public FailureItem build() {
      return new FailureItem(
          reason,
          message,
          stackTrace,
          causeType);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(160);
      buf.append("FailureItem.Builder{");
      buf.append("reason").append('=').append(JodaBeanUtils.toString(reason)).append(',').append(' ');
      buf.append("message").append('=').append(JodaBeanUtils.toString(message)).append(',').append(' ');
      buf.append("stackTrace").append('=').append(JodaBeanUtils.toString(stackTrace)).append(',').append(' ');
      buf.append("causeType").append('=').append(JodaBeanUtils.toString(causeType));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
