package calculations.controller.dto.filtration;

public enum FilterFields {
    CREATE_DATE("createDate"),
    INPUT("input"),
    RESULT("result"),
    OPERATION("operation");

    private final String fieldName;

    private FilterFields(String value) {
        fieldName = value;
    }

    public String getFieldName() {return fieldName;}
}
