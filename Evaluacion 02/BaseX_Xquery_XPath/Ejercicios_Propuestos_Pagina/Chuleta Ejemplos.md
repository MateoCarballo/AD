

        Pasos imprescindibles para trabajar con BaseX

1. Crear una CreadConexion
    
    ```
        try(BaseXClient session = new BaseXClient("localhost",
                                                    1984,
                                                    "admin",
                                                    "1234")){

        }
    ```

    