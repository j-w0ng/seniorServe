package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Preference
{
    private final int prefId;
    @NotBlank
    private final String prefName;
    private final String description;

    public Preference(@JsonProperty("pref_ID") int prefId,
                      @JsonProperty("pref_name") String prefName,
                      @JsonProperty("description") String description)
    {
        this.prefId = prefId;
        this.prefName = prefName;
        this.description = description;
    }

    public int getPrefId()
    {
        return prefId;
    }

    public String getPrefName()
    {
        return prefName;
    }

    public String getDescription()
    {
        return description;
    }
}
