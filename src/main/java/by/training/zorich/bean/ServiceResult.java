/**
 * Bean-class for exchange information between controller and service layer.
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.bean;

import java.io.Serializable;

public class ServiceResult implements Serializable {
    private static final long serialVersionUID = -2821767220673239414L;

    private Boolean resultOperation;
    private Object resultObject;
    private String resultMessage;

    public ServiceResult() {
    }

    public Boolean isDone() {
        return resultOperation;
    }

    public void setResultOperation(Boolean resultOperation) {
        this.resultOperation = resultOperation;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void clear() {
        resultOperation = null;
        resultObject = null;
        resultMessage = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceResult that = (ServiceResult) o;

        if (resultOperation != null ? !resultOperation.equals(that.resultOperation) : that.resultOperation != null) {
            return false;
        }
        if (resultObject != null ? !resultObject.equals(that.resultObject) : that.resultObject != null) {
            return false;
        }
        return resultMessage != null ? resultMessage.equals(that.resultMessage) : that.resultMessage == null;
    }

    @Override
    public int hashCode() {
        int result = resultOperation != null ? resultOperation.hashCode() : 0;
        result = 31 * result + (resultObject != null ? resultObject.hashCode() : 0);
        result = 31 * result + (resultMessage != null ? resultMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
               "resultOperation=" + resultOperation +
               ", resultObject=" + resultObject +
               ", resultMessage='" + resultMessage + '\'' +
               '}';
    }
}
