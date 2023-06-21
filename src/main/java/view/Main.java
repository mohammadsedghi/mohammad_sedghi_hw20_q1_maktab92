package view;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startProgramMenu();

        logger.info("Example log from {}", Main.class.getSimpleName());
        logger.info("Example log from {}", Menu.class.getClassLoader());
        logger.debug("dddddddddddd");
    }
}