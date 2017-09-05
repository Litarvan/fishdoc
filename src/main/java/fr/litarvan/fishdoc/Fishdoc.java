package fr.litarvan.fishdoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Yolo
 */
public class Fishdoc
{
    private static final Logger log = LoggerFactory.getLogger("Fishdoc");

    public static final String VERSION = "1.0.0";

    private FishdocProject project;
    private RootDoc doc;
    private File outputDir;

    public Fishdoc(FishdocProject project, RootDoc doc, File outputDir)
    {
        this.project = project;
        this.doc = doc;
        this.outputDir = outputDir;
    }

    public void start()
    {
        log.info("Fishdoc v{}", VERSION);
        log.info("--> Generating documentation for {} v{}\n", project.getName(), project.getVersion());

        for (ClassDoc cl : doc.classes())
        {
            System.out.println(cl.commentText());
        }
    }
}
