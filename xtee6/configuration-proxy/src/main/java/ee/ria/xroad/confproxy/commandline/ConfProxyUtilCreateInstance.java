package ee.ria.xroad.confproxy.commandline;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;

import ee.ria.xroad.common.SystemProperties;
import ee.ria.xroad.confproxy.ConfProxyProperties;

import static ee.ria.xroad.confproxy.ConfProxyProperties.CONF_INI;

/**
 * Utility tool for creating a new configuration proxy instance
 * with default settings.
 */
public class ConfProxyUtilCreateInstance extends ConfProxyUtil {

    static final int DEFAULT_VALIDITY_INTERVAL_SECONDS = 600;

    /**
     * Constructs a confproxy-create-instance utility program instance.
     */
    ConfProxyUtilCreateInstance() {
        super("confproxy-create-instance");
        getOptions()
            .addOption(PROXY_INSTANCE);
    }

    @Override
    final void execute(final CommandLine commandLine)
            throws Exception {
        if (commandLine.hasOption(PROXY_INSTANCE.getOpt())) {
            String instance = commandLine
                    .getOptionValue(PROXY_INSTANCE.getOpt());
            System.out.println("Generating configuration directory for "
                    + "instance '" + instance + "' ...");
            String basePath = SystemProperties.getConfigurationProxyConfPath();
            Path instancePath = Paths.get(basePath, instance);
            Files.createDirectories(instancePath);
            Path confPath = instancePath.resolve(CONF_INI);
            try {
                Files.createFile(confPath);
            } catch (FileAlreadyExistsException ex) {
                fail("Configuration for instance '" + instance
                        + "' already exists, aborting. ", ex);
            }
            ConfProxyProperties conf = new ConfProxyProperties(instance);
            System.out.println("Populating '" + CONF_INI
                    + "' with default values ...");
            conf.setValidityIntervalSeconds(DEFAULT_VALIDITY_INTERVAL_SECONDS);
            System.out.println("Done.");
        } else {
            printHelp();
        }
    }
}
