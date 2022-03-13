package DataModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Pathology {
    private IntegerProperty pt_id;
    private StringProperty testName;
    private StringProperty description;

    public Pathology(int pt_id, String testName, String description) {
        this.pt_id.set(pt_id);
        this.testName.set(testName);
        this.description.set(description);
    }

    public int getPt_id() {
        return pt_id.get();
    }

    public IntegerProperty pt_idProperty() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id.set(pt_id);
    }

    public String getTestName() {
        return testName.get();
    }

    public StringProperty testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
