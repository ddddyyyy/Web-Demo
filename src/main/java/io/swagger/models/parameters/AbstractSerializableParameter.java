//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.swagger.models.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.models.properties.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@JsonPropertyOrder({"name", "in", "description", "required", "type", "items", "collectionFormat", "default", "maximum", "exclusiveMaximum", "minimum", "exclusiveMinimum", "maxLength", "minLength", "pattern", "maxItems", "minItems", "uniqueItems", "multipleOf"})
public abstract class AbstractSerializableParameter<T extends io.swagger.models.parameters.AbstractSerializableParameter<T>> extends AbstractParameter implements SerializableParameter {
    private static final Logger LOGGER = LoggerFactory.getLogger(io.swagger.models.parameters.AbstractSerializableParameter.class);
    protected String type;
    protected String format;
    protected String collectionFormat;
    protected Property items;
    protected Boolean exclusiveMaximum;
    protected BigDecimal maximum;
    protected Boolean exclusiveMinimum;
    protected BigDecimal minimum;
    protected String example;
    private Integer maxItems;
    private Integer minItems;
    protected Boolean allowEmptyValue;
    @JsonIgnore
    protected List<String> _enum;
    public Integer maxLength;
    public Integer minLength;
    public String pattern;
    public Boolean uniqueItems;
    public Number multipleOf;
    @JsonIgnore
    protected String defaultValue;

    public AbstractSerializableParameter() {
    }

    public T property(Property property) {
        this.setProperty(property);
        return this.castThis();
    }

    public T type(String type) {
        this.setType(type);
        return this.castThis();
    }

    public T format(String format) {
        this.setFormat(format);
        return this.castThis();
    }

    public T description(String description) {
        this.setDescription(description);
        return this.castThis();
    }

    public T name(String name) {
        this.setName(name);
        return this.castThis();
    }

    public T required(boolean required) {
        this.setRequired(required);
        return this.castThis();
    }

    public T collectionFormat(String collectionFormat) {
        this.setCollectionFormat(collectionFormat);
        return this.castThis();
    }

    public T example(String example) {
        this.setExample(example);
        return this.castThis();
    }

    public T allowEmptyValue(Boolean allowEmpty) {
        this.setAllowEmptyValue(allowEmpty);
        return this.castThis();
    }

    public T readOnly(Boolean readOnly) {
        this.setReadOnly(readOnly);
        return this.castThis();
    }

    @JsonIgnore
    protected String getDefaultCollectionFormat() {
        return "csv";
    }

    public T items(Property items) {
        this.items = items;
        return this.castThis();
    }

    public T _enum(List<String> value) {
        this._enum = value;
        return this.castThis();
    }

    @JsonIgnore
    public List<String> getEnum() {
        return this._enum;
    }

    public void setEnum(List<String> _enum) {
        this._enum = _enum;
    }

    @JsonProperty("enum")
    public List<Object> getEnumValue() {
        if (this._enum == null) {
            return null;
        } else if (this._enum.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<Object> oList = new ArrayList(this._enum.size());
            Iterator var2;
            String s;
            if (!"integer".equals(this.type) && !"number".equals(this.type)) {
                if ((this.type == null || "array".equals(this.type)) && this.items != null) {
                    var2 = this._enum.iterator();

                    while (var2.hasNext()) {
                        s = (String) var2.next();

                        try {
                            if (this.items instanceof StringProperty) {
                                oList.add(s);
                            } else if (this.items instanceof IntegerProperty) {
                                oList.add(Integer.valueOf(s));
                            } else if (this.items instanceof LongProperty) {
                                oList.add(Long.valueOf(s));
                            } else if (this.items instanceof FloatProperty) {
                                oList.add(Float.valueOf(s));
                            } else if (this.items instanceof DoubleProperty) {
                                oList.add(Double.valueOf(s));
                            } else if (this.items instanceof BaseIntegerProperty) {
                                oList.add(Integer.valueOf(s));
                            } else if (this.items instanceof DecimalProperty) {
                                oList.add(Double.valueOf(s));
                            } else {
                                oList.add(s);
                            }
                        } catch (NumberFormatException var5) {
                            LOGGER.warn(String.format("Illegal enum value %s for parameter type %s", s, this.type), var5);
                            oList.add(s);
                        }
                    }
                } else {
                    var2 = this._enum.iterator();

                    while (var2.hasNext()) {
                        s = (String) var2.next();
                        oList.add(s);
                    }
                }
            } else {
                var2 = this._enum.iterator();

                while (var2.hasNext()) {
                    s = (String) var2.next();

                    try {
                        if ("int32".equals(this.format)) {
                            oList.add(Integer.valueOf(s));
                        } else if ("int64".equals(this.format)) {
                            oList.add(Long.valueOf(s));
                        } else if ("double".equals(this.format)) {
                            oList.add(Double.valueOf(s));
                        } else if ("float".equals(this.format)) {
                            oList.add(Float.valueOf(s));
                        } else if ("integer".equals(this.type)) {
                            oList.add(Integer.valueOf(s));
                        } else if ("number".equals(this.type)) {
                            oList.add(Double.valueOf(s));
                        }
                    } catch (NumberFormatException var6) {
                        LOGGER.warn(String.format("Illegal enum value %s for parameter type %s", s, this.type), var6);
                        oList.add(s);
                    }
                }
            }

            return oList;
        }
    }

    public void setEnumValue(List<?> enumValue) {
        if (enumValue == null) {
            this._enum = null;
        } else if (enumValue.isEmpty()) {
            this._enum = Collections.emptyList();
        } else {
            List<String> sList = new ArrayList(enumValue.size());
            Iterator var3 = enumValue.iterator();

            while (var3.hasNext()) {
                Object item = var3.next();
                sList.add(item.toString());
            }

            this._enum = sList;
        }
    }

    public Property getItems() {
        return this.items;
    }

    public void setItems(Property items) {
        this.items = items;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
        if (ArrayProperty.isType(type)) {
            if (this.getCollectionFormat() == null) {
                this.setCollectionFormat(this.getDefaultCollectionFormat());
            }
        } else {
            this.setCollectionFormat((String) null);
        }

    }

    public String getCollectionFormat() {
        return this.collectionFormat;
    }

    public void setCollectionFormat(String collectionFormat) {
        this.collectionFormat = collectionFormat;
    }

    public void setProperty(Property property) {
        this.setType(property.getType());
        this.format = property.getFormat();
        if (property instanceof StringProperty) {
            StringProperty string = (StringProperty) property;
            this.setEnum(string.getEnum());
        } else if (property instanceof IntegerProperty) {
            this.setEnumValue(((IntegerProperty) property).getEnum());
        } else if (property instanceof LongProperty) {
            this.setEnumValue(((LongProperty) property).getEnum());
        } else if (property instanceof FloatProperty) {
            this.setEnumValue(((FloatProperty) property).getEnum());
        } else if (property instanceof DoubleProperty) {
            this.setEnumValue(((DoubleProperty) property).getEnum());
        } else if (property instanceof ArrayProperty) {
            ArrayProperty array = (ArrayProperty) property;
            this.setItems(array.getItems());
        }

    }

    public Object getDefaultValue() {
        if (this.defaultValue == null) {
            return null;
        } else {
            try {
                if ("integer".equals(this.type)) {
                    return new Integer(this.defaultValue);
                }

                if ("number".equals(this.type)) {
                    return new BigDecimal(this.defaultValue);
                }
            } catch (Exception var2) {
                return null;
            }

            return this.defaultValue;
        }
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getDefault() {
        if (this.defaultValue != null && !this.defaultValue.isEmpty()) {
            try {
                if ("integer".equals(this.type)) {
                    return Long.valueOf(this.defaultValue);
                }

                if ("number".equals(this.type)) {
                    return Double.valueOf(this.defaultValue);
                }

                if ("boolean".equals(this.type) && ("true".equalsIgnoreCase(this.defaultValue) || "false".equalsIgnoreCase(this.defaultValue))) {
                    return Boolean.valueOf(this.defaultValue);
                }
            } catch (NumberFormatException var2) {
                LOGGER.warn(String.format("Illegal DefaultValue %s for parameter type %s", this.defaultValue, this.type), var2);
            }

            return this.defaultValue;
        } else {
            return null;
        }
    }

    public void setDefault(Object defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.toString();
    }

    public void setExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public BigDecimal getMaximum() {
        return this.maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public Boolean isExclusiveMinimum() {
        return this.exclusiveMinimum;
    }

    public void setExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public BigDecimal getMinimum() {
        return this.minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public Integer getMaxItems() {
        return this.maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }

    public Integer getMinItems() {
        return this.minItems;
    }

    public void setMinItems(Integer minItems) {
        this.minItems = minItems;
    }

    public Boolean getAllowEmptyValue() {
        return this.allowEmptyValue;
    }

    public void setAllowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
    }

    @JsonProperty("x-example")
    public Object getExample() {
        if (this.example == null) {
            return null;
        } else {
            try {
                if ("integer".equals(this.type) && !example.isEmpty()) {
                    return Long.valueOf(this.example);
                }

                if ("number".equals(this.type) && !example.isEmpty()) {
                    return Double.valueOf(this.example);
                }

                if ("boolean".equals(this.type) && ("true".equalsIgnoreCase(this.example) || "false".equalsIgnoreCase(this.defaultValue))) {
                    return Boolean.valueOf(this.example);
                }
            } catch (NumberFormatException var2) {
                LOGGER.warn(String.format("Illegal DefaultValue %s for parameter type %s", this.defaultValue, this.type), var2);
            }

            return this.example;
        }
    }

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMinLength() {
        return this.minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Boolean isUniqueItems() {
        return this.uniqueItems;
    }

    public void setUniqueItems(Boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public Number getMultipleOf() {
        return this.multipleOf;
    }

    public void setMultipleOf(Number multipleOf) {
        this.multipleOf = multipleOf;
    }

    public Boolean isExclusiveMaximum() {
        return this.exclusiveMaximum;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @JsonIgnore
    private T castThis() {
        return (T) this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            io.swagger.models.parameters.AbstractSerializableParameter<?> other = (io.swagger.models.parameters.AbstractSerializableParameter) obj;
            if (this._enum == null) {
                if (other._enum != null) {
                    return false;
                }
            } else if (!this._enum.equals(other._enum)) {
                return false;
            }

            if (this.collectionFormat == null) {
                if (other.collectionFormat != null) {
                    return false;
                }
            } else if (!this.collectionFormat.equals(other.collectionFormat)) {
                return false;
            }

            if (this.defaultValue == null) {
                if (other.defaultValue != null) {
                    return false;
                }
            } else if (!this.defaultValue.equals(other.defaultValue)) {
                return false;
            }

            if (this.example == null) {
                if (other.example != null) {
                    return false;
                }
            } else if (!this.example.equals(other.example)) {
                return false;
            }

            if (this.exclusiveMaximum == null) {
                if (other.exclusiveMaximum != null) {
                    return false;
                }
            } else if (!this.exclusiveMaximum.equals(other.exclusiveMaximum)) {
                return false;
            }

            if (this.exclusiveMinimum == null) {
                if (other.exclusiveMinimum != null) {
                    return false;
                }
            } else if (!this.exclusiveMinimum.equals(other.exclusiveMinimum)) {
                return false;
            }

            if (this.format == null) {
                if (other.format != null) {
                    return false;
                }
            } else if (!this.format.equals(other.format)) {
                return false;
            }

            if (this.items == null) {
                if (other.items != null) {
                    return false;
                }
            } else if (!this.items.equals(other.items)) {
                return false;
            }

            if (this.maxItems == null) {
                if (other.maxItems != null) {
                    return false;
                }
            } else if (!this.maxItems.equals(other.maxItems)) {
                return false;
            }

            if (this.maxLength == null) {
                if (other.maxLength != null) {
                    return false;
                }
            } else if (!this.maxLength.equals(other.maxLength)) {
                return false;
            }

            if (this.maximum == null) {
                if (other.maximum != null) {
                    return false;
                }
            } else if (!this.maximum.equals(other.maximum)) {
                return false;
            }

            if (this.minItems == null) {
                if (other.minItems != null) {
                    return false;
                }
            } else if (!this.minItems.equals(other.minItems)) {
                return false;
            }

            if (this.minLength == null) {
                if (other.minLength != null) {
                    return false;
                }
            } else if (!this.minLength.equals(other.minLength)) {
                return false;
            }

            if (this.minimum == null) {
                if (other.minimum != null) {
                    return false;
                }
            } else if (!this.minimum.equals(other.minimum)) {
                return false;
            }

            if (this.multipleOf == null) {
                if (other.multipleOf != null) {
                    return false;
                }
            } else if (!this.multipleOf.equals(other.multipleOf)) {
                return false;
            }

            if (this.pattern == null) {
                if (other.pattern != null) {
                    return false;
                }
            } else if (!this.pattern.equals(other.pattern)) {
                return false;
            }

            if (this.type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!this.type.equals(other.type)) {
                return false;
            }

            if (this.uniqueItems == null) {
                if (other.uniqueItems != null) {
                    return false;
                }
            } else if (!this.uniqueItems.equals(other.uniqueItems)) {
                return false;
            }

            if (this.allowEmptyValue != null) {
                if (!this.allowEmptyValue.equals(other.allowEmptyValue)) {
                    return false;
                }
            } else if (other.allowEmptyValue != null) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this._enum == null ? 0 : this._enum.hashCode());
        result = 31 * result + (this.collectionFormat == null ? 0 : this.collectionFormat.hashCode());
        result = 31 * result + (this.defaultValue == null ? 0 : this.defaultValue.hashCode());
        result = 31 * result + (this.example == null ? 0 : this.example.hashCode());
        result = 31 * result + (this.exclusiveMaximum == null ? 0 : this.exclusiveMaximum.hashCode());
        result = 31 * result + (this.exclusiveMinimum == null ? 0 : this.exclusiveMinimum.hashCode());
        result = 31 * result + (this.format == null ? 0 : this.format.hashCode());
        result = 31 * result + (this.items == null ? 0 : this.items.hashCode());
        result = 31 * result + (this.maxItems == null ? 0 : this.maxItems.hashCode());
        result = 31 * result + (this.maxLength == null ? 0 : this.maxLength.hashCode());
        result = 31 * result + (this.maximum == null ? 0 : this.maximum.hashCode());
        result = 31 * result + (this.minItems == null ? 0 : this.minItems.hashCode());
        result = 31 * result + (this.minLength == null ? 0 : this.minLength.hashCode());
        result = 31 * result + (this.minimum == null ? 0 : this.minimum.hashCode());
        result = 31 * result + (this.multipleOf == null ? 0 : this.multipleOf.hashCode());
        result = 31 * result + (this.pattern == null ? 0 : this.pattern.hashCode());
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        result = 31 * result + (this.uniqueItems == null ? 0 : this.uniqueItems.hashCode());
        result = 31 * result + (this.allowEmptyValue != null ? this.allowEmptyValue.hashCode() : 0);
        return result;
    }
}
