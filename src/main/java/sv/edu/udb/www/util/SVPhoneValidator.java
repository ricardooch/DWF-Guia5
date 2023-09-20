package sv.edu.udb.www.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
@FacesValidator("sv.edu.udb.www.util.SVPhoneValidator")
public class SVPhoneValidator implements Validator{
    private static final String CUSTOM_PATTERN = "^[2|3|6|7]{1}\\d{3}-\\d{4}$";
    private Pattern pattern;
    private Matcher matcher;
    public SVPhoneValidator() {
        pattern = Pattern.compile(CUSTOM_PATTERN);
    }
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws
            ValidatorException {
        matcher = pattern.matcher(o.toString());
        if(!matcher.matches()){
            FacesMessage msg =
                    new FacesMessage("Validación de teléfono falló.",
                            "Formato incorrecto.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}