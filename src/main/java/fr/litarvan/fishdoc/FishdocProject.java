package fr.litarvan.fishdoc;

/**
 * Youluu
 */
public class FishdocProject
{
    private String name;
    private String version;
    private String icon;

    public FishdocProject(String name, String version, String icon)
    {
        this.name = name;
        this.version = version;
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
