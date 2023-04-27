//package fhi360.it.assetverify.config;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ClientForwardController {
//    public static final String FORBID_EXTENSION_PATTERN = "/{file:[^\\.]*}";
//
//    // Folders with trailing separator.
//    public static final String TRAILING_DIR_PATTERN = "/{traillingDir:.*}/";
//
//    public static final String ALLOW_ROOT_PATTERN = "/{rootDir:^(?!graphql)[^\\.]*}";
//
//    @GetMapping(value = {
//        ALLOW_ROOT_PATTERN,
//        ALLOW_ROOT_PATTERN + FORBID_EXTENSION_PATTERN,
//        ALLOW_ROOT_PATTERN + "/*" + FORBID_EXTENSION_PATTERN,
//        "/*" + FORBID_EXTENSION_PATTERN,
//        "/*/*" + FORBID_EXTENSION_PATTERN,
//        TRAILING_DIR_PATTERN,
//        "/*" + TRAILING_DIR_PATTERN,
//        "/*/*" + TRAILING_DIR_PATTERN,
//        "/*/*/*/*/{*remaining}"
//    })
//    public String forward() {
//        return "forward:/";
//    }
//
//    //@GetMapping(value = "/")
//    public String forwardIndex() {
//        return "forward:/index.html";
//    }
//}
