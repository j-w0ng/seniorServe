package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.List;

public class TaskFilter {
    private List<String> inputLocations;
    private List<String> cityChecklist;
    private List<String> provChecklist;
    private List<String> prefChecklist;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    public TaskFilter(@JsonProperty("inputLocations") List<String> inputLocations,
                      @JsonProperty("cityChecklist") List<String> cityChecklist,
                      @JsonProperty("provChecklist") List<String> provChecklist,
                      @JsonProperty("prefChecklist") List<String> prefChecklist,
                      @JsonProperty("startDate") Date startDate,
                      @JsonProperty("endDate") Date endDate) {
        this.inputLocations = inputLocations;
        this.cityChecklist = cityChecklist;
        this.provChecklist = provChecklist;
        this.prefChecklist = prefChecklist;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<String> getInputLocations() {
        return inputLocations;
    }

    public List<String> getCityChecklist() {
        return cityChecklist;
    }

    public List<String> getProvChecklist() {
        return provChecklist;
    }

    public List<String> getPrefChecklist() {
        return prefChecklist;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
