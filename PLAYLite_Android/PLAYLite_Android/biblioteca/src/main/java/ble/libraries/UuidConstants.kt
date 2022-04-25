package ble.libraries

import UuidUtils.convert16to128UUID
import java.util.*

class UuidConstants {
    companion object {

        val BLE_SERVICE_DeviceInformation: UUID = UUID.fromString(convert16to128UUID("180A"))
            //DEVICEINFORMATION.CHARACTERISTICS
            val BLE_CHARACTERISTIC_ManufacturerName: UUID = UUID.fromString(convert16to128UUID("2A29"))
            val BLE_CHARACTERISTIC_ModelNumber: UUID = UUID.fromString(convert16to128UUID("2A24"))
            val BLE_CHARACTERISTIC_VersionSwDevice: UUID = UUID.fromString(convert16to128UUID("2A28"))
            val BLE_CHARACTERISTIC_VersionHwDevice: UUID = UUID.fromString(convert16to128UUID("2A27"))

        val BLE_SERVICE_SiliconLabsOta: UUID = UUID.fromString("1D14D6EE-FD63-4FA1-BFA4-8F47B42119F0")
            // OTA CHARACTERISTICS
            val BLE_CHARACTERISTIC_OTADataAttributeUUIDString: UUID = UUID.fromString("984227f3-34fc-4045-a5d0-2c581f81a153")
            val BLE_CHARACTERISTIC_OTAControlAttributeUUIDString: UUID = UUID.fromString("f7bf3564-fb6d-4e53-88a4-5e37e0326063")

        var BLE_SERVICE_CURRENT_TIME: UUID = UUID.fromString(convert16to128UUID("1805"))
            //CURRENT TIME-CHARACTERISTICS
            val BLE_CHARACTERISTIC_CurrentTime: UUID = UUID.fromString(convert16to128UUID("2A2B"))

        val BLE_SERVICE_Programming: UUID = UUID.fromString("d17cf492-e997-4d99-b542-6c065251e62b")
            //PROGRAMMING.CHARACTERISTICS
            val BLE_CHARACTERISTIC_SequenceSettings: UUID = UUID.fromString("941bc3d3-a3a4-4f31-8f8a-60d205cd5e1f")
            val BLE_CHARACTERISTIC_StartProgramming: UUID = UUID.fromString("cccdf256-e4b9-496a-b425-3ff84a3d4228")

        val BLE_SERVICE_Configurations: UUID = UUID.fromString("8bdfda14-588d-4f25-a857-94d91450f75a")
            //CONFIGURATION CHARACTERISTICS
            val BLE_CHARACTERISTIC_GPS_MODE_AUTO: UUID = UUID.fromString("c7789645-a229-4905-b7ba-1c9a753b2abf")
            val BLE_CHARACTERISTIC_GPS_CONNECTION_PARAMETERS: UUID = UUID.fromString("b7544b0d-567d-4bb5-bc4e-a879e4afde51")
            val BLE_CHARACTERISTIC_Id_Imei: UUID = UUID.fromString("0c7ba840-35de-46dd-9b85-9f83b7293f48")
            val BLE_CHARACTERISTIC_Latitude: UUID = UUID.fromString("70755adc-3edb-43b1-a5fc-cffdc163c047")
            val BLE_CHARACTERISTIC_Longitude: UUID = UUID.fromString("13a41b0a-554d-42cf-996d-35636a7456fe")
            val BLE_CHARACTERISTIC_Name: UUID = UUID.fromString("2b238b94-d817-4244-ae6d-736151c33735")
            val BLE_CHARACTERISTIC_Id_ICCD: UUID = UUID.fromString("38c5a303-00dd-42e5-bfb0-edcaa3ea0725")
            val BLE_CHARACTERISTIC_MODELO_MODULO_COM: UUID = UUID.fromString("c48befb2-c3d3-4d3c-a56a-1092fe206948")

        val BLE_SERVICE_PowerAnalyzer: UUID = UUID.fromString("74db60ac-3974-4f8a-af7d-378f1484abb8")
            //POWER ANALYZER CHARACTERISTICS
            val BLE_CHARACTERISTIC_Datalogger: UUID = UUID.fromString("25f7f963-21f8-46d6-b080-1f0a30d84e34")
            val BLE_CHARACTERISTIC_Datalogger_Reset: UUID = UUID.fromString("89c5142b-a71e-4f7a-8e69-f2e212063ec6")
            val BLE_CHARACTERISTIC_Datalogger_Time_Sec: UUID = UUID.fromString("0d6ff607-3d30-447f-9e7f-a036a3f46ff1")
            val BLE_CHARACTERISTIC_VariablesAnalizador: UUID = UUID.fromString("eec08d42-5f99-4a56-ad08-69f900c341c8")
            val BLE_CHARACTERISTIC_AnalizadorResetParametros: UUID = UUID.fromString("7dcbde8d-3f85-495f-a746-3fcfafc18b80")
            val BLE_CHARACTERISTIC_AnalizadorConfigRegistros: UUID = UUID.fromString("2aae51e5-eab0-4797-b932-cc47878d6170")

        val BLE_SERVICE_COMMUNICATIONS: UUID = UUID.fromString("3eefc01c-98a1-43c0-aee7-2e784710b8aa")
            //COMMUNICATIONS CHARACTERISTICS
            val BLE_CHARACTERISTIC_VERSION_MODULO_COM: UUID = UUID.fromString("406ce7b9-9882-4a27-9d37-f79c77df68a6")
            val BLE_CHARACTERISTIC_RED_NIVEL_COBERTURA: UUID = UUID.fromString("0ed124b7-22ad-4c09-94ba-124bce9342cc")
            val BLE_CHARACTERISTIC_RED_ESTADO_CONEXION: UUID = UUID.fromString("c6fa52d7-7b6c-4ccb-88f6-57aa42682c12")
            val BLE_CHARACTERISTIC_RED_OPERADOR_ACTUAL: UUID = UUID.fromString("885d3357-fcd1-4423-b2b4-dcfc4fb06443")
            val BLE_CHARACTERISTIC_RED_MODO_CONEXION: UUID = UUID.fromString("b778e184-9bc2-4509-9a9d-4faf3006af92")
            val BLE_CHARACTERISTIC_REDES_OPERADORES_DISPONIBLES: UUID = UUID.fromString("24d2e85c-a2da-4a36-aa2a-75eade7fed29")
            val BLE_CHARACTERISTIC_COM_SERVER_ENABLE: UUID = UUID.fromString("75f4c2b7-cd57-4e29-936e-462447b4a844")
            val BLE_CHARACTERISTIC_COM_SERVER_ADDRESS: UUID = UUID.fromString("06e65073-3e06-4a42-a6ad-20198f0f9c48")
            val BLE_CHARACTERISTIC_COM_PORT: UUID = UUID.fromString("1b700e4a-cef3-44fa-a64c-fe2e016bebc1")
            val BLE_CHARACTERISTIC_COM_SIM_PIN: UUID = UUID.fromString("a9c4f2c5-d738-4c70-9ec9-6c34611821f4")
            val BLE_CHARACTERISTIC_COM_APN: UUID = UUID.fromString("84115fc8-7f11-43f4-9a1d-de69887583e4")
            val BLE_CHARACTERISTIC_Store_COM_Parameters: UUID = UUID.fromString("e38f4e48-4e4c-468b-a063-2590a2bc37b1")

        val BLE_SERVICE_SecurityPassKey: UUID = UUID.fromString("133a39bc-3515-445e-9504-310087e778fa")
            //CONFIGURATION CHARACTERISTICS
            val BLE_CHARACTERISTIC_New_PassKey: UUID = UUID.fromString("a6a884be-0436-4dce-afb9-b58e7b8f7175")
            val BLE_CHARACTERISTIC_Old_PassKey: UUID = UUID.fromString("56ca2e4a-df30-4a80-9805-ba39df058364")

        val BLE_SERVICE_Advanced: UUID = UUID.fromString("734a5c61-3543-4755-a5f9-9be378d6c337")
            //ADVANCED.CHARACTERISTICS
            val BLE_CHARACTERISTIC_Rele1: UUID = UUID.fromString("1986194c-e7a2-44ae-9682-e36359d1a114")
            val BLE_CHARACTERISTIC_Rele2: UUID = UUID.fromString("2dc375ee-9636-4bee-83f8-b72f2babe6a8")
            val BLE_CHARACTERISTIC_Rele3: UUID = UUID.fromString("f0aa5285-4bf1-4485-920f-1cf6086315fc")
            val BLE_CHARACTERISTIC_ActivarModeDaliToChronoStep: UUID = UUID.fromString("0da886a5-a575-4e26-8974-3663a5722523")
            val BLE_CHARACTERISTIC_ModoMantenimiento: UUID = UUID.fromString("2fe0346f-6b57-417f-9b79-c055414a7e24")

        val BLE_SERVICE_RelojCrepuscular: UUID = UUID.fromString("c6bc3861-8323-463e-9a7d-b59ba22ed952")
            //RELOJ CREPUSCULAR CHARACTERISTICS
            val BLE_CHARACTERISTIC_CREPUSCULAR_ENABLE: UUID = UUID.fromString("e409cb56-f3c6-4b03-831d-9ad80b3e7753")
            val BLE_CHARACTERISTIC_CREPUSCULAR_DST_DAYLIGHTSAVINGTIME: UUID = UUID.fromString("057fa84e-6eca-4d95-9eb6-574affdb6dfb")
            val BLE_CHARACTERISTIC_CREPUSCULAR_TIME_ZONE: UUID = UUID.fromString("d14cd8be-83e4-4de8-a179-2f1f25d5313c")
            val BLE_CHARACTERISTIC_CREPUSCULAR_OFFSET_SUNRISE: UUID = UUID.fromString("72355166-9ce7-4363-a9e1-68d53e59d4bd")
            val BLE_CHARACTERISTIC_CREPUSCULAR_OFFSET_SUNSET: UUID = UUID.fromString("f3f3bde4-5424-466d-85ba-da64452bf859")
            val BLE_CHARACTERISTIC_CREPUSCULAR_SUNRISE_TIME_HH_MM: UUID = UUID.fromString("48319659-0126-475d-9e13-7f23763984ea")
            val BLE_CHARACTERISTIC_CREPUSCULAR_SUNSET_TIME_HH_MM: UUID = UUID.fromString("88fd87f5-5100-4283-b163-7368e6f93ff2")
            val BLE_CHARACTERISTIC_CREPUSCULAR_DST_INICIO_VERANO: UUID = UUID.fromString("c4c631f5-0994-4615-a71a-1416d9b610d4")
            val BLE_CHARACTERISTIC_CREPUSCULAR_DST_INICIO_INVIERNO: UUID = UUID.fromString("371cab9a-bb5c-4f1e-9ef8-734a99a20db3")
            val BLE_CHARACTERISTIC_CREPUSCULAR_DST_IN_RANGE: UUID = UUID.fromString("1c21fcd9-d716-43bc-b883-4a652a30cad8")
            val BLE_CHARACTERISTIC_CREPUSCULAR_DST_AUTOMATIC: UUID = UUID.fromString("52f9fdb6-ac70-4869-a971-1c86cf799533")
            val BLE_CHARACTERISTIC_CREPUSCULAR_HORA_ACTUAL_RELOJ_ASTRONOMICO: UUID = UUID.fromString("bc92499b-6151-49c0-a7ef-e74f7314305d")

        val BLE_SERVICE_TemperaturaHardware: UUID = UUID.fromString("315ffbfe-a3eb-4b77-8925-17cb25088c68")
            //TEMPERATURA MEDIDA HARDWARE
            val BLE_CHARACTERISTIC_TEMPERATURA_MEDIDA_HARDWARE: UUID = UUID.fromString("e024e35e-833d-4496-a6bd-e5d859cb88ce")

        val BLE_SERVICE_Battery_Service: UUID = UUID.fromString(convert16to128UUID("180F"))
            //BATERIA
            val BLE_CHARACTERISTIC_BATTERY_DAY_CHANGE: UUID= UUID.fromString("611c83bb-0bdc-46a2-93f3-6253fd30f153")
            val BLE_CHARACTERISTIC_BATTERY_LEVEL: UUID = UUID.fromString(convert16to128UUID("2A19"))

        val BLE_SERVICE_Lightness: UUID = UUID.fromString("21c77a4b-3c8a-434a-b4c3-fea8222d4457")
            //LIGHTNESS-CHARACTERISTICS
            val BLE_CHARACTERISTIC_LightnessDataWrite: UUID = UUID.fromString("75277ed7-b01b-4337-9006-3a3b64b75998")
            val BLE_CHARACTERISTIC_LightnessDataRead: UUID = UUID.fromString("397c9361-e6c7-413a-bf3c-9604cba66e51")

        val BLE_SERVICE_DeadTime: UUID = UUID.fromString("b4eb14fa-4b8a-42ad-9047-bd0374b81944")
            //DEADTIME-CHARACTERISTICS
            val BLE_CHARACTERISTIC_DeadTimeData: UUID = UUID.fromString("6762f406-2083-4c83-92c1-a46c98a93c61")

        val BLE_SERVICE_Perfiles: UUID = UUID.fromString("91d4f701-1735-4aa1-b4de-e99ae377a43b")
            //PERFILES-CHARACTERISTICS
            val BLE_CHARACTERISTIC_PERFIL_PARAMETROS_BASICOS: UUID = UUID.fromString("470ebc3d-40f3-4ed1-bc2f-b188805059ba")
            val BLE_CHARACTERISTIC_PERFIL_REGULACION: UUID = UUID.fromString("77ff0bb1-636e-434e-b692-e44d36266069")
            val BLE_CHARACTERISTIC_PERFIL_CANALES: UUID = UUID.fromString("4e47e99b-025b-4ba4-a0a9-047ee1191d62")

        val BLE_SERVICE_Reset: UUID = UUID.fromString("fdc20395-30e1-4323-80e9-5f00c96eebeb")
            //RESET-CHARACTERISTICS
            val BLE_CHARACTERISTIC_Reset: UUID = UUID.fromString("bab07ede-f6dc-45e1-8335-e53328a70d79")




        val BLE_CHARACTERISTIC_StoreSettingsParameters: UUID = UUID.fromString("85608b42-a74d-408e-8666-e257c182fe23")








    }
}
