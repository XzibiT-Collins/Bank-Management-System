module com.example.banksystem {
    requires javafx.controls;
    requires javafx.fxml;

    // Allows FXMLLoader to inject fields and access methods via reflection
    opens com.example.banksystem to javafx.fxml;
    opens com.example.banksystem.bankPackages to javafx.fxml;  // For any classes in bankPackages if needed

    exports com.example.banksystem;
    exports com.example.banksystem.bankPackages;
}

