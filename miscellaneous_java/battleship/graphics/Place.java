package graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

/**
 * Graphical place that can be hit or contain part of a ship
 * @author AlexanderWu
 *
 */
public class Place extends AbstractButton implements Accessible {

	private static final long serialVersionUID = -2032739186271379702L;

    /**
     * @see #getUIClassID
     * @see #readObject
     */
    private static final String uiClassID = "ButtonUI";

    /**
     * Creates a place with no set text or icon.
     */
    public Place() {
        this(null, null);
    }
    
    /**
     * Creates a place with an icon.
     *
     * @param icon  the Icon image to display on the button
     */
    public Place(Icon icon) {
        this(null, icon);
    }
    
    /**
     * Creates a place with initial text and an icon.
     *
     * @param text  the text of the button
     * @param icon  the Icon image to display on the button
     */
    public Place(String text, Icon icon) {
        // Create the model
        setModel(new DefaultButtonModel());

        // initialize
        init(text, icon);
    }

    /**
     * Resets the UI property to a value from the current look and
     * feel.
     *
     * @see JComponent#updateUI
     */
    public void updateUI() {
        setUI((ButtonUI)UIManager.getUI(this));
    }


    /**
     * Returns a string that specifies the name of the L&amp;F class
     * that renders this component.
     *
     * @return the string "ButtonUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     * @beaninfo
     *        expert: true
     *   description: A string that specifies the name of the L&amp;F class.
     */
    public String getUIClassID() {
        return uiClassID;
    }


    /**
     * Gets the value of the <code>defaultButton</code> property,
     * which if <code>true</code> means that this button is the current
     * default button for its <code>JRootPane</code>.
     * Most look and feels render the default button
     * differently, and may potentially provide bindings
     * to access the default button.
     *
     * @return the value of the <code>defaultButton</code> property
     * @see JRootPane#setDefaultButton
     * @see #isDefaultCapable
     * @beaninfo
     *  description: Whether or not this button is the default button
     */
    public boolean isDefaultButton() {
        return false;
    }

    /**
     * Gets the value of the <code>defaultCapable</code> property.
     *
     * @return the value of the <code>defaultCapable</code> property
     * @see #setDefaultCapable
     * @see #isDefaultButton
     * @see JRootPane#setDefaultButton
     */
    public boolean isDefaultCapable() {
        return isDefaultCapable();
    }

    /**
     * Sets the <code>defaultCapable</code> property,
     * which determines whether this button can be
     * made the default button for its root pane.
     * The default value of the <code>defaultCapable</code>
     * property is <code>true</code> unless otherwise
     * specified by the look and feel.
     *
     * @param defaultCapable <code>true</code> if this button will be
     *        capable of being the default button on the
     *        <code>RootPane</code>; otherwise <code>false</code>
     * @see #isDefaultCapable
     * @beaninfo
     *        bound: true
     *    attribute: visualUpdate true
     *  description: Whether or not this button can be the default button
     */
    public void setDefaultCapable(boolean defaultCapable) {
        boolean oldDefaultCapable = this.isDefaultCapable();
        this.setDefaultCapable(defaultCapable);
        firePropertyChange("defaultCapable", oldDefaultCapable, defaultCapable);
    }

    /**
     * Overrides <code>JComponent.removeNotify</code> to check if
     * this button is currently set as the default button on the
     * <code>RootPane</code>, and if so, sets the <code>RootPane</code>'s
     * default button to <code>null</code> to ensure the
     * <code>RootPane</code> doesn't hold onto an invalid button reference.
     */
    public void removeNotify() {
        super.removeNotify();
    }

    /**
     * See readObject() and writeObject() in JComponent for more
     * information about serialization in Swing.
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }


    /**
     * Returns a string representation of this <code>JButton</code>.
     * This method is intended to be used only for debugging purposes, and the
     * content and format of the returned string may vary between
     * implementations. The returned string may be empty but may not
     * be <code>null</code>.
     *
     * @return  a string representation of this <code>JButton</code>
     */
    protected String paramString() {
        String defaultCapableString = (isDefaultCapable() ? "true" : "false");

        return super.paramString() +
            ",defaultCapable=" + defaultCapableString;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> associated with this
     * <code>JButton</code>. For <code>JButton</code>s,
     * the <code>AccessibleContext</code> takes the form of an
     * <code>AccessibleJButton</code>.
     * A new <code>AccessibleJButton</code> instance is created if necessary.
     *
     * @return an <code>AccessibleJButton</code> that serves as the
     *         <code>AccessibleContext</code> of this <code>JButton</code>
     * @beaninfo
     *       expert: true
     *  description: The AccessibleContext associated with this Button.
     */
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJButton();
        }
        return accessibleContext;
    }

    /**
     * This class implements accessibility support for the
     * <code>JButton</code> class.  It provides an implementation of the
     * Java Accessibility API appropriate to button user-interface
     * elements.
     * <p>
     * <strong>Warning:</strong>
     * Serialized objects of this class will not be compatible with
     * future Swing releases. The current serialization support is
     * appropriate for short term storage or RMI between applications running
     * the same version of Swing.  As of 1.4, support for long term storage
     * of all JavaBeans&trade;
     * has been added to the <code>java.beans</code> package.
     * Please see {@link java.beans.XMLEncoder}.
     */
    @SuppressWarnings("serial")
    protected class AccessibleJButton extends AccessibleAbstractButton {

        /**
         * Get the role of this object.
         *
         * @return an instance of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PUSH_BUTTON;
        }
    } // inner class AccessibleJButton

}
