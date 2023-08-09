package dev.milo.form.utils;

import org.geysermc.cumulus.form.Form;
import org.geysermc.cumulus.form.impl.FormDefinition;
import org.geysermc.cumulus.form.impl.FormDefinitions;

public class FormUtils {

    private static final FormDefinitions formDefinitions = FormDefinitions.instance();

    public static String createFormData(Form form) {
        FormDefinition<Form, ?, ?> definition = formDefinitions.definitionFor(form);
        return definition.codec().jsonData(form);
    }

    /**
     * Handle a form response
     *
     * @param form The form that is being handled
     * @param data The raw modal form response data
     * @return whether the modal has been handled
     * @throws Exception if we cannot handle the modal form data
     */
    public static boolean callResponseConsumer(Form form, String data) throws Exception {
        if (form != null) {
            try {
                formDefinitions.definitionFor(form).handleFormResponse(form, data);
            } catch (Exception e) {
                throw new Exception("Unable to handle form response!");
            }
            return true;
        }
        return false;
    }
}
