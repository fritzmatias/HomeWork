package fritzmatias.patterns.buider.imp;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * <p>
 * Mutable, Property Map<br>
 * Defines key, value to dynamically be used into any class, abstract or
 * builder.
 * </p>
 * 
 * @author user
 * @since Version: 2
 * @param <T>
 *            the type to be build.
 */
public abstract class PropertyMap {
	private Map<String, Object> properties = Maps.newHashMap();

	public PropertyMap() {
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param map
	 */
	public PropertyMap(Map<String, Object> map) {
		this.properties = map;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author user
	 * @since Version: 2
	 * @param key
	 * @param value
	 * @throws IllegalArgumentException
	 *             if the build() method was call
	 * @return this
	 */
	protected PropertyMap set(String key, Object value) {
		this.properties.put(key, value);
		return this;
	}

	/**
	 * <p>
	 * General Getter.<br>
	 * The call implies a required request, so an exception is thrown if the key
	 * has no value associated.
	 * </p>
	 * 
	 * @see
	 * @author user
	 * @since Version: 1
	 * @param key
	 * @return the value to be cast, or an IllegalArgumentException if
	 *         value=null
	 */
	protected Object get(String key) {
		Object o = this.properties.get(key);
		if (o == null) {
			throw new IllegalArgumentException("the required property : " + key + " was empty.");
		}
		return o;
	}

	/**
	 * <p>
	 * Never throws an exception because the value not exist.
	 * </p>
	 * 
	 * @author user
	 * @since Version: 1
	 * @param key
	 * @return null if the value not exist.
	 */
	protected Object getOptional(String key) {
		return this.properties.get(key);
	}

	/**
	 * <p>
	 * This methods is similar to {@link #getOptional(String)} but with the
	 * guarantee of return some expected value.<br>
	 * This method never throws an exception that could be thrown by {@link #get(String)}
	 * </p>
	 * 
	 * @author matias
	 * @since Version: 1
	 * @param indepTerm
	 * @param valueOf
	 * @return the value asociated to key or the defaultObject.
	 */
	protected <T> T getOptionalOrDefault(String key, T defaultObject) {
		@SuppressWarnings("unchecked")
		T o = (T)this.getOptional(key);
		return o != null ? o : defaultObject;
	}

	@Override
	public int hashCode() {
		return properties.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		
		PropertyMap other = (PropertyMap) obj;
		return properties.equals(other.properties);
	}

	
}
