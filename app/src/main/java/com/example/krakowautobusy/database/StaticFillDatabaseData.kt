package com.example.krakowautobusy.database

import android.database.sqlite.SQLiteDatabase

class StaticFillDatabaseData(override var instance: Database,
                             override var insertVehicleDatabase: Insert_db_VehicleTypeInterface,
                             override var insertBusStopDatabase: Insert_db_BusStopInterface,
                             override var insertLineDatabase: Insert_db_LineInterface
) :FillDatabaseDataInterface {
    private var db: SQLiteDatabase = instance.writableDatabase



    init{
        this.instance=instance
        this.db = instance.writableDatabase
        this.insertVehicleDatabase=StaticInsert_db_VehicleType()
        this.insertBusStopDatabase=StaticInsert_db_BusStop()
        this.insertLineDatabase=StaticInsert_db_Line()

    }


    override fun fill_LineBusStop() {
        super.fill_LineBusStop()
    }


    override fun fill_VehicleTypeTable() {
        super.fill_VehicleTypeTable()

        val listOfVehicle=ArrayList<Insert_db_VehicleTypeInterface.VehicleDatabaseRow>()
        listOfVehicle.add(Insert_db_VehicleTypeInterface.VehicleDatabaseRow("BUS"))
        listOfVehicle.add(Insert_db_VehicleTypeInterface.VehicleDatabaseRow("TRAM"))


        insertVehicleDatabase.insertVehicleRowToDb(instance.writableDatabase,listOfVehicle[0])
        insertVehicleDatabase.insertVehicleRowToDb(instance.writableDatabase,listOfVehicle[1])

    }

    override fun fill_LineTable() {
        super.fill_LineTable()
        val listLine=ArrayList<Insert_db_LineInterface.LineRow>()
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838593,"100",8095258838875242683,8095258838875242726,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838594,"101",8095258838875242683,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838595,"102",8095258838875242541,8095258838875242691,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838596,"103",8095258838875242796,8095258838875242893,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838597,"105",8095258838875243585,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838618,"106",8095258838875242926,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838599,"107",8095258838875242989,8095258838875243387,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838600,"109",8095258838875242691,8095258838875243503,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838601,"110",8095258838875242851,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838603,"112",8095258838875242918,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838604,"113",8095258838875243084,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839171,"114",8095258838875244113,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838789,"116",8095258838875244027,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838606,"117",8095258838875242845,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838607,"120",8095258838875242511,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838729,"121",8095258838875244926,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838608,"122",8095258838875242820,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838609,"123",8095258838875244574,8095258838875242767,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838610,"124",8095258838875243860,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838611,"125",8095258838875243067,8095258838875243077,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838612,"127",8095258838875243715,8095258838875244227,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838613,"128",8095258838875242568,8095258838875243554,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838614,"129",8095258838875243585,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838615,"130",8095258838875243585,8095258838875242532,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838616,"131",8095258838875242873,8095258838875242829,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838617,"133",8095258838875243559,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839172,"134",8095258838875243503,8095258838875242609,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838619,"136",8095258838875243150,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839173,"137",8095258838875243693,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839174,"138",8095258838875242532,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839175,"139",8095258838875245008,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839374,"140",8095258838875242546,8095258838875243473,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839176,"141",8095258838875242877,8095258838875242829,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839177,"142",8095258838875243145,8095258838875243120,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839178,"143",8095258838875242971,8095258838875244227,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839179,"144",8095258838875242532,8095258838875243864,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838647,"148",8095258838875244112,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839180,"149",8095258838875242893,8095258838875243669,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839182,"152",8095258838875242558,8095258838875243664,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838624,"153",8095258838875242973,8095258838875243559,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838622,"155",8095258838875243012,8095258838875243387,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839504,"156",8095258838875243044,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839184,"158",8095258838875242953,8095258838875244227,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839185,"159",8095258838875242558,8095258838875242769,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838788,"160",8095258838875242870,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839186,"162",8095258838875242925,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839187,"163",8095258838875242769,8095258838875243864,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839188,"164",8095258838875242522,8095258838875242998,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838623,"165",8095258838875243697,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839190,"168",8095258838875244113,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839191,"169",8095258838875242522,8095258838875243387,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839192,"171",8095258838875242856,8095258838875242829,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839193,"172",8095258838875242599,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839194,"173",8095258838875242532,8095258838875243559,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839195,"174",8095258838875242830,8095258838875242994,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839196,"175",8095258838875243680,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839438,"176",8095258838875244657,8095258838875242922,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839197,"178",8095258838875243136,8095258838875242767,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839198,"179",8095258838875243585,8095258838875242994,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839199,"181",8095258838875242887,8095258838875242829,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839200,"182",8095258838875243240,8095258838875243145,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839201,"183",8095258838875243067,8095258838875244671,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839202,"184",8095258838875242757,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838598,"189",8095258838875243503,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838761,"192",8095258838875242614,8095258838875243240,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839203,"193",8095258838875243245,8095258838875242890,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839204,"194",8095258838875242541,8095258838875243150,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839206,"202",8095258838875244549,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839207,"203",8095258838875243045,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839208,"204",8095258838875243858,8095258838875244728,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839209,"207",8095258838875242629,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838659,"208",8095258838875243711,8095258838875243240,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838660,"209",8095258838875244152,8095258838875242726,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838661,"210",8095258838875243637,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838662,"211",8095258838875242830,8095258838875243416,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838663,"212",8095258838875244547,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838664,"213",8095258838875243774,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838665,"214",8095258838875244968,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838666,"215",8095258838875243783,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838667,"217",8095258838875243252,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838668,"218",8095258838875243162,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839105,"219",8095258838875243752,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838669,"220",8095258838875243330,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838670,"221",8095258838875244574,8095258838875243404,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838671,"222",8095258838875243440,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838672,"223",8095258838875244594,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838673,"224",8095258838875244728,8095258838875243475,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838674,"225",8095258838875243026,8095258838875244582,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838676,"227",8095258838875244609,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839456,"228",8095258838875243759,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838677,"229",8095258838875244508,8095258838875242726,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838678,"230",8095258838875243205,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838679,"232",8095258838875244553,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838680,"233",8095258838875243486,8095258838875244756,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839366,"234",8095258838875244484,8095258838875243559,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838681,"235",8095258838875243453,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838683,"238",8095258838875242497,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838684,"239",8095258838875242726,8095258838875243129,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838685,"240",8095258838875244834,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838686,"242",8095258838875244391,8095258838875242830,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838687,"243",8095258838875243045,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838688,"244",8095258838875244728,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838689,"245",8095258838875243045,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838690,"247",8095258838875243802,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838691,"248",8095258838875244336,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838692,"249",8095258838875242726,8095258838875244681,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838693,"250",8095258838875244799,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838694,"252",8095258838875243711,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838695,"253",8095258838875243858,8095258838875244596,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839465,"254",8095258838875243026,8095258838875244158,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838696,"255",8095258838875243053,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838697,"257",8095258838875243807,8095258838875242546,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838698,"258",8095258838875244709,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838699,"259",8095258838875244681,8095258838875242726,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838700,"260",8095258838875243825,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838631,"262",8095258838875245059,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838701,"263",8095258838875243858,8095258838875243338,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839099,"264",8095258838875243423,8095258838875244574,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838702,"265",8095258838875244632,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838703,"267",8095258838875244800,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838704,"268",8095258838875242599,8095258838875244356,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838705,"269",8095258838875243465,8095258838875242726,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838706,"270",8095258838875243825,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838749,"271",8095258838875244547,8095258838875242829,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838707,"273",8095258838875244906,8095258838875244756,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839167,"274",8095258838875244429,8095258838875244227,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838708,"275",8095258838875244578,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838709,"277",8095258838875244620,8095258838875242546,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838710,"278",8095258838875244435,8095258838875242599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838711,"280",8095258838875243809,8095258838875242546,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838712,"283",8095258838875243045,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838755,"284",8095258838875245098,8095258838875243559,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838713,"285",8095258838875243096,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838714,"287",8095258838875243819,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838719,"293",8095258838875243777,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838756,"295",8095258838875244756,8095258838875244599,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838716,"297",8095258838875244363,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838757,"300",8095258838875243711,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838717,"301",8095258838875243404,8095258838875242546,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838718,"304",8095258838875244728,8095258838875243585,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838653,"307",8095258838875243697,8095258838875245039,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838758,"310",8095258838875243637,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838654,"337",8095258838875242546,8095258838875243809,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839210,"413",8095258838875242793,8095258838875242890,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838721,"422",8095258838875242820,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838722,"424",8095258838875242581,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839344,"433",8095258838875242994,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838922,"475",8095258838875243680,8095258838875243026,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838725,"482",8095258838875243240,8095258838875242767,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838726,"501",8095258838875244113,8095258838875242811,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838727,"502",8095258838875243503,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838728,"503",8095258838875243693,8095258838875243559,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838933,"511",8095258838875244113,8095258838875243145,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838873,"537",8095258838875242516,8095258838875243240,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838730,"572",8095258838875242599,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838620,"578",8095258838875243858,8095258838875242767,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838731,"601",8095258838875245008,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838732,"605",8095258838875243067,8095258838875244417,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838733,"608",8095258838875243150,8095258838875243165,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838754,"609",8095258838875242691,8095258838875243240,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838734,"610",8095258838875243026,8095258838875242568,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838735,"611",8095258838875244113,8095258838875242767,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838785,"612",8095258838875242918,8095258838875243697,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838736,"637",8095258838875242541,8095258838875243693,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838737,"642",8095258838875243665,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838739,"662",8095258838875243858,8095258838875243664,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838740,"664",8095258838875242599,8095258838875242769,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838741,"669",8095258838875242541,8095258838875244244,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839119,"708",8095258838875243026,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839270,"716",8095258838875243235,8095258838875242796,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839302,"720",8095258838875242511,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838759,"734",8095258838875242614,8095258838875243503,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839133,"735",8095258838875244159,8095258838875244227,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838635,"744",8095258838875242541,8095258838875243514,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838760,"792",8095258838875242622,8095258838875243240,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838743,"902",8095258838875243240,8095258838875243711,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838744,"903",8095258838875243045,8095258838875243858,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838745,"904",8095258838875244728,8095258838875243514,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305839087,"910",8095258838875242599,8095258838875243637,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838746,"915",8095258838875244582,8095258838875243136,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838979,"917",8095258838875244609,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838980,"937",8095258838875243809,8095258838875242541,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8095257447305838752,"999",8095258838875245076,8095258838875245079,Insert_db_VehicleTypeInterface.Vehicle.BUS))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874679,"3",8059230041856278712,8059230041856279368,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875566,"5",8059230041856278712,8059230041856278786,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874693,"18",8059230041856278712,8059230041856279100,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874687,"50",8059230041856279369,8059230041856278712,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874667,"19",8059230041856278714,8059230041856278842,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875578,"2",8059230041856278730,8059230041856278741,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875430,"4",8059230041856278740,8059230041856278786,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875431,"8",8059230041856278740,8059230041856278842,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875432,"13",8059230041856278726,8059230041856279368,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875433,"20",8059230041856278724,8059230041856279480,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874686,"24",8059230041856278740,8059230041856279369,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874682,"9",8059230041856279368,8059230041856278764,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875580,"10",8059230041856278842,8059230041856278794,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874694,"52",8059230041856278766,8059230041856279100,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874655,"1",8059230041856278741,8059230041856278786,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874670,"22",8059230041856278788,8059230041856278842,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875322,"21",8059230041856278766,8059230041856278794,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874683,"11",8059230041856279100,8059230041856279480,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875579,"6",8059230041856278724,8059230041856279369,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875567,"14",8059230041856278726,8059230041856278764,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875582,"17",8059230041856278714,8059230041856279100,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875568,"49",8059230041856279102,8059230041856279368,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874677,"62",8059230041856279100,8059230041856278881,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874678,"64",8059230041856278740,8059230041856278766,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286874680,"69",8059230041856278712,8059230041856279368,Insert_db_VehicleTypeInterface.Vehicle.TRAM))
        listLine.add(Insert_db_LineInterface.LineRow(8059228650286875379,"74",8059230041856278714,8059230041856278764,Insert_db_VehicleTypeInterface.Vehicle.TRAM))

        // insertLineDatabase.insertRow(instance.writableDatabase,listLine[0])

        for (line in listLine){
            insertLineDatabase.insertRow(instance.writableDatabase,line)
        }
    }


    override fun fill_BusStopTable() {
        super.fill_BusStopTable()

        val listOfBusStops=ArrayList<Insert_db_BusStopInterface.BusStopRow>()





        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242506,180355211,71525357,"Pasternik (nż)","15"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242665,180307038,71310417,"Balice Autostrada (nż)","221"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242857,180355770,72641363,"Resztówka","502"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243079,180353573,71605010,"Jasnogórska (nż)","832"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244269,180355788,72333072,"Grzegorza z Sanoka (nż)","3186"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244301,180357480,71907156,"Reduta (nż)","3192"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243331,179834882,71282750,"Rzozów Skotnica (nż)","1179"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243266,179802360,71040600,"Krzęcin Kapliczka (nż)","1098"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244598,179812594,71029293,"Krzęcin Szczęsna (nż)","3332"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244596,179648900,71166074,"Wola Radziszowska Pętla","3330"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243292,179670262,71237902,"Wola Radziszowska Centrum","1127"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243294,179653546,71191853,"Wola Radziszowska Chorzyny (nż)","1129"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243290,179665896,71296908,"Wola Radziszowska Most (nż)","1125"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243455,179674414,71251527,"Wola Radziszowska Podgaźle (nż)","1336"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243291,179676914,71272176,"Wola Radziszowska Lipki (nż)","1126"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243262,179775666,71157072,"Polanka Hallera Dwór","1093"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243263,179759880,71105400,"Grabie Polanka (nż)","1094"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243495,179740080,71061480,"Grabie Kuźnia (nż)","2492"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244599,179733348,71042652,"Grabie Pętla","3333"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244906,179758742,71165729,"Polanka Hallera Centrum","3482"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243324,179798324,71082473,"Krzęcin Cmentarz (nż)","1169"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243267,179797680,71060400,"Krzęcin Szkoła","1099"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243265,179796960,71068680,"Krzęcin Kościół","1096"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243264,179790660,71100468,"Krzęcin Sklep","1095"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243260,179795520,71200800,"Gołuchowice Jurczyce","1091"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243261,179785985,71176390,"Gołuchowice Sklep (nż)","1092"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243491,179778243,71210204,"Jurczyce Dom Ludowy","2488"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243492,179779760,71229227,"Jurczyce Szkoła (nż)","2489"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243259,179806129,71224366,"Gołuchowice","1090"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243494,179778600,71283960,"Radziszów Za Mogiłką (nż)","2491"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243493,179779533,71246289,"Jurczyce Podole (nż)","2490"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243258,179828614,71257315,"Rzozów Szkoła","1089"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243332,179803714,71294391,"Rzozów PKP (nż)","1180"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243333,179816393,71284113,"Rzozów Zagonnie (nż)","1181"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244508,180053896,70512273,"Kamień Szkoła","3279"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244691,180033649,70502245,"Kamień Remiza (nż)","3404"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244594,179880517,70663696,"Brzeźnica Dworzec","3328"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243276,179872889,70701792,"Brzeźnica Pasieka (nż)","1108"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244595,179866655,70737187,"Brzezinka Kuźnia (nż)","3329"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243393,180009138,70526531,"Rusocice Jałowce (nż)","1260"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244571,180017645,70517847,"Rusocice Stacja Paliw (nż)","3307"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244692,180017645,70517847,"Rusocice Granica (nż)","3405"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244572,179999170,70567077,"Rusocice Na Brzegu (nż)","3308"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243371,179980864,70583337,"Rusocice Skład (nż)","1229"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243367,179983289,70625918,"Rusocice Łęg (nż)","1225"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243394,179992992,70618802,"Rusocice Załęg (nż)","1261"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243372,180004068,70606764,"Rusocice Wieś","1230"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244573,179986500,70674516,"Kłokoczyn Machaczki (nż)","3309"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244570,179982560,70700457,"Kłokoczyn Szkoła (nż)","3306"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243740,179989156,70741393,"Czernichów Ratanice (nż)","2892"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243739,179983032,70766592,"Czernichów Ratanice Most (nż)","2891"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244693,180020629,70778708,"Przeginia Narodowa Bunar (nż)","3406"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244687,180042457,70767459,"Przeginia Narodowa Dwór (nż)","3400"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244694,180070480,70751343,"Przeginia Duchowna Kościół (nż)","3407"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244686,180080939,70746870,"Przeginia Duchowna Remiza","3399"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243349,180086163,70759531,"Przeginia Duchowna Szkoła (nż)","1198"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243359,180158906,70695748,"Rybna Dół (nż)","1213"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243457,180117978,70685780,"Rybna Zagórze (nż)","1341"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243467,180142911,70682925,"Rybna Dolna","1367"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243360,180123759,70721497,"Przeginia Duchowna (nż)","1214"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244811,180136716,70715172,"Rybna Ośrodek Zdrowia (nż)","3463"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244810,180149067,70715944,"Rybna Pawilon (nż)","3462"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244564,180177782,70728811,"Rybna Kościół","3299"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243733,180194597,70746492,"Rybna Szkoła (nż)","2885"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243732,180211813,70757619,"Rybna Nowy Świat Góra (nż)","2884"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243356,180221673,70764104,"Rybna Nowy Świat","1208"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244060,180233041,70771436,"Rybna Droga do Sanki (nż)","3103"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242710,180142511,71043535,"Kaszów Tyrałówka (nż)","284"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244684,179959318,71017021,"Wołowice Remiza (nż)","3397"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244984,179856237,70799198,"Jaśkowice Remiza (nż)","3535"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243279,179858384,70828594,"Jaśkowice PKP (nż)","1111"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243280,179858432,70874887,"Wielkie Drogi Trzebol (nż)","1112"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243375,179858613,70902860,"Wielkie Drogi Szkoła (nż)","1233"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244681,179953841,70835643,"Czernichów Rynek","3394"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243374,179853299,70923396,"Wielkie Drogi Ośrodek Zdrowia (nż)","1232"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243281,179847908,70961526,"Wielkie Drogi PKP (nż)","1113"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243774,179831144,71026911,"Krzęcin Pętla","2928"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244985,179851839,71000012,"Facimiech Pod Kolebą (nż)","3536"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244690,179958642,70928706,"Wołowice Niwka (nż)","3403"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243755,179959094,70968602,"Wołowice Pichonówka (nż)","2907"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243129,179951536,71020592,"Wołowice Kapliczka","909"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243738,179975699,70799501,"Czernichów Zakamycze (nż)","2890"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243368,179999991,70803528,"Czernichów Bór (nż)","1226"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244689,179971488,70866965,"Czernichów Studzienki (nż)","3402"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243352,179971447,70878679,"Czernichów Stacja Paliw (nż)","1203"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244569,179998365,70905961,"Zagacie Górka (nż)","3305"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243737,180015271,70928278,"Zagacie Witkówki (nż)","2889"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243354,179962179,70996372,"Wołowice Sklep (nż)","1205"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243116,179977361,71031664,"Wołowice Szkoła","893"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243734,180055827,70986522,"Kaszów Wyźrał (nż)","2886"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244567,180078384,71023043,"Kaszów Szkoła nr 2 (nż)","3303"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243451,179880120,71070120,"Ochodza Dom Ludowy (nż)","1329"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243452,179892360,71085600,"Ochodza Staw (nż)","1330"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244597,179872920,71075520,"Ochodza Dwór","3331"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243325,179852314,71080488,"Zelczyna Szkoła (nż)","1170"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244986,179857533,71092216,"Zelczyna PKP","3537"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244987,179864889,71129507,"Zelczyna (nż)","3538"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243453,179920080,71102160,"Ochodza Odwiśle","1331"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244390,179934178,71082088,"Wołowice Zawierzbie (nż)","3229"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243777,179917990,71147281,"Kopanka Pętla","2931"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244388,179944332,71116776,"Wołowice Grotowa Skrzyżowanie (nż)","3227"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244392,179941497,71132245,"Wołowice Grotowa (nż)","3231"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244389,179933789,71159968,"Jeziorzany Górskie Domy (nż)","3228"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244988,179873786,71177425,"Borek Szlachecki Kościół (nż)","3539"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244989,179876760,71199745,"Borek Szlachecki (nż)","3540"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243275,179886600,71226720,"Skawina Podbory (nż)","1107"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243256,179862899,71300233,"Rzozów Baseny (nż)","1087"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243257,179848354,71283849,"Rzozów I (nż)","1088"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243778,179927634,71186924,"Kopanka Druga (nż)","2932"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244797,179916609,71224213,"Kopanka Ofiar Katynia","3451"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243752,179953710,71202715,"Jeziorzany Pętla","2904"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243487,179935306,71209794,"Kopanka Kapliczka (nż)","2484"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243271,179899804,71269683,"Skawina Piłsudskiego","1103"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243716,179923782,71246089,"Skawina Pileckiego","2867"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243272,179902483,71294929,"Skawina Elektrownia (nż)","1104"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243488,179941261,71243633,"Kopanka Szkoła","2485"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243698,179938123,71262420,"Kopanka Kościół (nż)","2813"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243489,179933595,71284819,"Skawina Robotnicza","2486"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244602,179954532,71302176,"Skawina Piastowska (nż)","3336"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244600,179953092,71293032,"Skawina Żwirowa (nż)","3334"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243130,179994831,71104918,"Dąbrowa Szlachecka Krzyż (nż)","910"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244688,179983351,71042609,"Wołowice Skrzyżowanie (nż)","3401"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243127,179985494,71077379,"Dąbrowa Szlachecka Figurka (nż)","907"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243379,179997587,71060899,"Dąbrowa Szlachecka Krzemiennik","1237"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242723,180004940,71119497,"Rączna Osiedle (nż)","306"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243750,180012677,71160206,"Rączna Boisko (nż)","2902"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243362,180049331,71081525,"Rączna Podlas (nż)","1216"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244568,180090609,71050339,"Kaszów Wielka Droga (nż)","3304"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243363,180062748,71085920,"Rączna Dzikowiec (nż)","1217"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243469,180039731,71124502,"Rączna Kościół (nż)","1370"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244683,180028976,71163017,"Rączna Remiza (nż)","3396"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242718,180055019,71168786,"Rączna Kapliczka (nż)","296"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243751,179978823,71195749,"Jeziorzany Rondo (nż)","2903"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243384,180008650,71202016,"Ściejowice Na Bagnach (nż)","1246"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244386,180021024,71204357,"Ściejowice Remiza (nż)","3225"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244387,179996105,71207274,"Ściejowice Kościel (nż)","3226"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244682,180040678,71190745,"Rączna Bażanty (nż)","3395"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244695,180059580,71193399,"Rączna Gołębiec (nż)","3408"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243100,180065721,71235411,"Piekary Górne","868"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243341,180073635,71257319,"Piekary Pałac (nż)","1189"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244676,180083184,71261693,"Piekary Centrum","3389"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244677,180092998,70884138,"Nowa Wieś Szlachecka Pętla","3390"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244685,180096005,70852224,"Nowa Wieś Szlachecka Jesionka (nż)","3398"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243361,180137995,70861537,"Czułówek (nż)","1215"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243465,180152287,70847820,"Czułówek Kapliczka","1365"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243727,180219463,70898830,"Czułów Szkoła","2879"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244678,180097857,70910321,"Nowa Wieś Szlachecka Kościół (nż)","3391"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244679,180111016,70935694,"Nowa Wieś Szlachecka Sklep (nż)","3392"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244680,180122376,70960922,"Nowa Wieś Szlachecka Kapliczka (nż)","3393"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242709,180141500,71012149,"Kaszów","283"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242708,180139955,70995178,"Kaszów II (nż)","282"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244907,180129686,70978751,"Kaszów Na Górce (nż)","3483"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244559,180214813,70933358,"Czułów Sklep (nż)","3294"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244560,180214022,70963398,"Czułów Granica (nż)","3295"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244561,180215583,70998932,"Mników Remiza (nż)","3296"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244562,180215604,71016156,"Mników Sklep (nż)","3297"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244152,180248040,70946856,"Mników Skały","2715"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244151,180229140,70995852,"Mników Pańska Góra (nż)","2714"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244153,180238392,70989336,"Mników Zarynnie (nż)","3104"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242671,180286610,70996305,"Chrosna Sklep (nż)","229"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242672,180286816,71028981,"Chrosna Kapliczka (nż)","230"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243759,180295841,70954490,"Chrosna Pętla","2911"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244339,180323280,70930476,"Brzoskwinia Pętla","3201"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242681,180335710,70956466,"Brzoskwinia Kamyk (nż)","240"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242670,180292822,70977672,"Chrosna Studnia (nż)","228"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244957,180344072,70986526,"Brzoskwinia Remiza","3521"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243633,180338147,71024192,"Brzoskwinia Góra (nż)","2695"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243852,180226834,71294325,"Balice Olszanica Bory","3013"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243735,180111597,71097616,"Liszki Wołowska Droga (nż)","2887"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242711,180134129,71081123,"Kaszów Łysa Góra (nż)","285"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242712,180127225,71133047,"Liszki Krzyżówka","286"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242713,180138648,71165832,"Liszki UG","287"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243922,180215325,71071899,"Cholerzyn Granica (nż)","3056"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243921,180209936,71116547,"Cholerzyn Zagórze (nż)","3055"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242686,180208506,71143121,"Cholerzyn Skrzyżowanie (nż)","252"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242687,180213012,71167995,"Cholerzyn Sklep (nż)","253"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242689,180152748,71239752,"Kryspinów Sanka (nż)","257"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243747,180124543,71184987,"Liszki Tyniecka (nż)","2899"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243746,180102510,71232046,"Piekary Borki (nż)","2898"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243748,180112587,71210557,"Piekary Dół (nż)","2900"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242714,180140013,71186771,"Liszki I (nż)","289"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242690,180157212,71260056,"Kryspinów","258"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242697,180094113,71270904,"Piekary Sklep (nż)","269"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243745,180104760,71284073,"Piekary Korea (nż)","2897"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244812,180188609,71220505,"Cholerzyn Chałupki (nż)","3464"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244563,180209838,71191586,"Cholerzyn Skwer (nż)","3298"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243684,180171004,71250327,"Budzyń Plaża Główna (nż)","2780"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243683,180179107,71278509,"Budzyń Zalew na Piaskach (nż)","2779"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242622,180222583,71301372,"Olszanica Bory","161"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244154,180251532,71091468,"Morawica Rudki (nż)","3105"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244393,180232524,71056584,"Mników Granica (nż)","3232"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244673,180270547,71111938,"Morawica Centrum","3386"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244674,180288730,71118138,"Morawica Szkoła (nż)","3387"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242673,180297877,71059974,"Morawica Mostek (nż)","231"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242674,180289863,71098950,"Morawica Krzyżówka","232"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242679,180326019,71046736,"Brzoskwinia Koziary (nż)","238"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244675,180292350,71151085,"Aleksandrowice Centrum","3388"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242678,180293771,71169073,"Aleksandrowice Osiedle (nż)","236"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243761,180342918,71169594,"Burów Centrum","2913"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243711,180259164,71284248,"Kraków Airport","2861"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243851,180245393,71283475,"Balice Medweckiego (nż)","3012"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243760,180297711,71199005,"Balice Winna Góra (nż)","2912"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244699,180336471,71188338,"Burów Balica (nż)","3414"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243603,180327281,71214883,"Balice Grzybów (nż)","2635"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243604,180323768,71232139,"Balice Leśna (nż)","2636"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242666,180301325,71245144,"Balice Szkoła","222"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244672,180301869,71240191,"Balice Instytut Zootechniki (nż)","3385"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244700,180312130,71248435,"Balice Remiza (nż)","3415"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242664,180334261,71294027,"Szczyglice","220"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242663,180353497,71299200,"Skała Kmity (nż)","219"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242521,180335649,71831378,"Bociana","34"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242592,180234167,71823937,"Lubicz","126"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242593,180244109,71831630,"Uniwersytet Ekonomiczny","127"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243095,179830879,71907069,"Ochojno Skrzyżowanie","860"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244835,180296101,71825994,"UR al. 29 Listopada","3469"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243289,179664326,71334040,"Wola Radziszowska Mała Ostra Góra (nż)","1124"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244397,179681616,71359740,"Radziszów Podlesie Las (nż)","3233"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243057,179698721,71346856,"Radziszów Leśniczówka (nż)","797"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244629,179654976,71372484,"Wola Radziszowska Kapelanka (nż)","3362"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244037,179663724,71402184,"Radziszów Szpital","3075"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243482,179673966,71379246,"Radziszów Stare Sanatorium (nż)","2477"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243337,179717207,71306982,"Radziszów Zawodzie (nż)","1185"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243338,179706148,71309817,"Radziszów PKP","1186"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243058,179709945,71357560,"Radziszów Podlesie (nż)","798"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243336,179738373,71309352,"Radziszów Podwale (nż)","1184"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243048,179749659,71354380,"Radziszów Włosanka (nż)","786"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243049,179772572,71335149,"Radziszów Zadworze (nż)","787"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243335,179766276,71314110,"Radziszów Zacisze (nż)","1183"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243050,179780762,71313791,"Radziszów Centrum","788"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243334,179785284,71307849,"Radziszów Kładka (nż)","1182"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243679,179788680,71303400,"Radziszów Cmentarz (nż)","2774"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243051,179808685,71320431,"Radziszów Kamionna (nż)","789"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243109,179807782,71430713,"Buków Klin (nż)","882"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243110,179807301,71447816,"Buków Szkoła (nż)","883"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243111,179803116,71473143,"Kulerzów (nż)","884"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243112,179802379,71504111,"Kulerzów Wąwóz (nż)","885"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243797,179799142,71530396,"Mogilany Kopce (nż)","2953"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244588,179818602,71530011,"Chorowice Pętla","3324"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244578,179688960,71650440,"Włosań Centrum","3314"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244577,179760892,71662651,"Mogilany Krótka (nż)","3313"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243888,179688996,71650872,"Włosań Dział Wschodni (nż)","3053"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243064,179704800,71691840,"Włosań Krzyżówka (nż)","808"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244576,179760427,71695800,"Konary Królowej Polski (nż)","3312"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243054,179793439,71587289,"Mogilany Rynek","792"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243055,179790337,71614577,"Mogilany Ośrodek Zdrowia (nż)","793"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243056,179771350,71628096,"Mogilany Wiadukt (nż)","794"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243501,179776811,71615172,"Mogilany Cicha (nż)","2499"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243114,179801393,71581030,"Mogilany Cmentarz (nż)","888"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243065,179724960,71712000,"Włosań Kopań (nż)","809"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243887,179749296,71702928,"Konary Pod Sosenką (nż)","3052"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244582,179763415,71808266,"Świątniki Górne Urząd Miasta i Gminy","3318"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244632,179799811,71716662,"Konary Pętla","3364"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243459,179767559,71739022,"Konary Sęk (nż)","1347"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243788,179794701,71798601,"Wrząsowice Zielona (nż)","2944"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244589,179779498,71793970,"Świątniki Górne Krakowska (nż)","3325"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244584,179766795,71764231,"Świątniki Górne Buk (nż)","3320"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244711,179811983,71809423,"Wrząsowice Bonifraterska (nż)","3426"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243089,179763816,71834396,"Świątniki Centrum","847"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244581,179763816,71834396,"Świątniki Górne Centrum","3317"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244579,179763261,71882422,"Świątniki Górne Skrzyżowanie (nż)","3315"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243880,179755052,71905784,"Świątniki Górne Piasna Górka (nż)","3051"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243087,179753616,71933457,"Rzeszotary Zalesie (nż)","842"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244580,179766477,71864087,"Świątniki Górne Bielowicza Szkoła","3316"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243682,179778371,71884203,"Rzeszotary Pod Dębiną (nż)","2778"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243092,179777200,71901100,"Rzeszotary Szkoła (nż)","857"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243091,179781288,71941230,"Rzeszotary Trafo (nż)","856"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244586,179804588,71902948,"Rzeszotary Remiza (nż)","3322"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243093,179803109,71930936,"Rzeszotary Polanki (nż)","858"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243784,179756141,71979872,"Rzeszotary Panciawa (nż)","2940"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243783,179765562,71997660,"Rzeszotary Panciawa Pętla","2939"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243656,179789578,71960535,"Rzeszotary Tempo (nż)","2732"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244585,179796796,71976292,"Rzeszotary Dworska","3321"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242924,180068244,71433264,"Kostrze","601"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243478,179896715,71385285,"Skawina Ajka","2473"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244930,179864507,71340033,"Skawina Os. Radziszowskie (nż)","3502"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245058,179859366,71337968,"Skawina Groble (nż)","3598"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243497,179835054,71334706,"Skawina Nad Potokiem (nż)","2494"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243477,179889918,71361844,"Skawina Radziszowska (nż)","2472"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244587,179832994,71406365,"Buków Pętla","3323"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243706,179863079,71409986,"Skawina Bukowska (nż)","2854"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243045,179886686,71387386,"Skawina","779"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243255,179905696,71330460,"Skawina Koncentraty","1086"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243273,179902800,71303582,"Skawina Zachodnia (nż)","1105"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243847,179908552,71312068,"Skawina Energetyków Przejazd PKP (nż)","3008"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243200,179913231,71344016,"Skawina Sąd","1008"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243199,179912101,71362548,"Skawina Szkoła","1007"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243483,179920921,71352172,"Skawina Tyniecka Osiedle","2478"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244756,179915364,71357652,"Skawina SCK","3441"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244601,179936604,71306244,"Skawina Żwirownia (nż)","3335"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243490,179934510,71316625,"Skawina Energetyków (nż)","2487"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243201,179952577,71331280,"Skawina Wojska Polskiego","1011"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243485,179959863,71319723,"Skawina Samborek Most","2480"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243484,179945301,71337346,"Skawina Tyniecka","2479"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243479,179907068,71390403,"Skawina Popiełuszki","2474"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243480,179910734,71391974,"Skawina Cmentarz","2475"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243713,179896365,71372207,"Skawina Centrum Sportowe","2863"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243046,179910790,71380566,"Skawina Rynek","781"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243298,179897218,71419128,"Skawina Starostwo Powiatowe","1134"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243270,179907120,71412840,"Skawina Korabnicka Szkoła (nż)","1102"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243297,179902526,71404110,"Skawina 29 Listopada","1133"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243047,179937865,71414011,"Skawina Rzepnik","785"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243776,179878131,71460422,"Skawina Kopernika (nż)","2930"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243775,179885801,71438836,"Skawina Ogrody (nż)","2929"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243299,179883900,71470296,"Skawina Cmentarz Komunalny","1137"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243269,179907120,71451360,"Skawina Korabnicka (nż)","1101"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243703,179899535,71474773,"Skawina Wyspiańskiego Szkoła (nż)","2820"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243268,179908200,71483400,"Skawina Wyspiańskiego","1100"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243486,179909247,71490162,"Skawina Korabniki","2481"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243481,179958889,71440557,"Skawina Podlipki","2476"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243644,179901360,71528760,"Brzyczyna","2708"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243705,179915858,71501153,"Skawina Graniczna (nż)","2853"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243771,179897868,71554647,"Libertów Droga do Sidziny (nż)","2925"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243680,179929444,71543125,"Nałkowskiej","2775"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243681,179934593,71565171,"Petrażyckiego (nż)","2776"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243044,179958542,71539257,"Sidzina","772"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244231,179967686,71327517,"Skawina Tyniecka Rzepnik (nż)","3173"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244593,179988263,71347343,"Skawina Podgórki","3327"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243241,179998384,71344355,"Janasówka (nż)","1062"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242918,180030000,71332086,"Tyniec Kamieniołom","591"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242919,180037020,71323191,"Bogucianka","592"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242920,180055023,71312392,"Tyniec","593"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243541,180066006,71342140,"Bolesława Śmiałego","2561"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243543,180088436,71398547,"Kolna (nż)","2563"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243545,180035209,71428450,"Bagienna","2565"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243542,180078987,71379596,"Tyniecka Autostrada (nż)","2562"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242925,180022977,71440069,"Podgórki Tynieckie","603"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243575,179982099,71465289,"Wrony","2598"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244027,180021931,71491399,"Kozienicka","3063"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243576,179964951,71494278,"Warchałowskiego","2599"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244169,179965786,71511023,"Sapalskiego (nż)","3152"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244028,180020677,71512738,"Orszy-Broniewskiego","3064"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243253,180012644,71530046,"Baczyńskiego (nż)","1083"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243041,180024215,71539698,"Skotniki","766"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242923,180083950,71450515,"Dąbrowa","600"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243586,180092503,71464174,"Ślaskiego (nż)","2610"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242926,180058886,71551839,"Skotniki Szkoła","604"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243040,180043136,71552894,"Brücknera","765"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244250,180031680,71548200,"Skotniki Kościół","3181"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243470,180081110,71519062,"Fort Winnica (nż)","1371"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243471,180072253,71536075,"Winnicka (nż)","1372"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243707,179848639,71687113,"Gaj Kwiatowa (nż)","2856"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243708,179842131,71652102,"Gaj Rudawa (nż)","2857"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243709,179840399,71629920,"Gaj Zgody (nż)","2858"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243053,179838505,71602243,"Gaj Zadziele","791"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243052,179858367,71604105,"Gaj Szkoła","790"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243645,179898127,71581360,"Libertów Krzyżówka","2710"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243646,179899884,71607949,"Libertów Działy (nż)","2711"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243081,179938479,71586131,"Żyzna (nż)","834"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244033,179940859,71603133,"Libertowska (nż)","3071"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244032,179943520,71626798,"Petrażyckiego Osiedle (nż)","3070"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243642,179904521,71664391,"Libertów","2706"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243643,179901047,71633865,"Libertów Szkoła","2707"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243678,179920440,71673120,"Libertów Zgodna (nż)","2772"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243606,179926795,71678971,"Libertów Dolny (nż)","2638"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243068,179949498,71649297,"Podgaje","812"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243607,179941600,71686106,"Ważewskiego","2639"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244228,179951983,71673466,"Taklińskiego (nż)","3172"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243039,179951302,71690274,"Opatkowice","761"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244036,179954339,71687672,"Kłuszyńska","3074"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243194,179860500,71704845,"Lusina Pętla","1000"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243060,179838720,71747208,"Lusina Górka (nż)","801"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244646,179838720,71747208,"Libertów Świetlista","3373"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243779,179832900,71760954,"Lusina Zielona","2935"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243780,179852448,71745167,"Lusina Dobrzyczany (nż)","2936"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243347,179867262,71722176,"Lusina Łany (nż)","1196"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243195,179882868,71746876,"Lusina Szkoła","1001"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243196,179894292,71750004,"Lusina Spacerowa","1002"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243376,179835531,71804178,"Wrząsowice Kowaleca (nż)","1234"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243791,179846391,71806736,"Wrząsowice Pileckiego (nż)","2947"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243790,179856421,71799333,"Wrząsowice Centrum","2946"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243781,179870212,71765869,"Lusina Kraśnik (nż)","2937"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243782,179889321,71769931,"Lusina Zagrody (nż)","2938"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243789,179869224,71804958,"Wrząsowice Wrzosowa (nż)","2945"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245144,179889183,71806157,"Wrząsowice Spacerowa (nż)","3197"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244591,179912296,71722932,"Lusina Krakowska (nż)","3326"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243197,179905075,71740512,"Lusina Dwór","1003"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243004,179905145,71760759,"Lusina Dolna (nż)","722"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244566,179922546,71756126,"Lusina Krakówka (nż)","3301"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243071,179933309,71711597,"Smoleńskiego","816"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243080,179953182,71700779,"Poronińska (nż)","833"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243602,179946589,71709998,"Opatkowice Zadworze","2634"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244696,179912808,71804808,"Ukraina (nż)","723"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243003,179901894,71810976,"Zbydniowice","721"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244161,179926129,71799842,"Wróblowice","3148"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244163,179926705,71817046,"Aleksandrowicza (nż)","3150"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243008,179939264,71772982,"Chałubińskiego (nż)","728"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243010,179949577,71784439,"Pytlasińskiego","730"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243009,179942688,71800668,"Wróblowice Szkoła","729"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243042,180027072,71571857,"Babińskiego","767"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243150,179995184,71629765,"Pod Fortem","944"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243043,180025785,71610575,"Kobierzyn","768"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243078,180026053,71626876,"Komuny Paryskiej","830"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243033,180003746,71661746,"Forteczna","754"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243034,179998872,71682987,"Zawiszy","755"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243035,180001936,71685606,"Os. Kliny","756"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244926,179983476,71681832,"Kliny Poranne","3498"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244927,179994528,71692092,"Zagaje (nż)","3499"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244928,179983044,71691948,"Chorzowska (nż)","3500"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243141,180004493,71641194,"Kliny Zacisze","935"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243030,180023354,71643195,"Zawiła (nż)","751"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243032,180016329,71660899,"Borkowska","753"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243458,180045819,71581987,"Bunscha (nż)","1346"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243632,180059776,71573370,"Mochnaniec (nż)","2694"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243858,180065950,71601095,"Czerwone Maki P+R","3038"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243143,180055259,71649349,"Lubostroń (nż)","937"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243031,180040445,71634148,"Skośna","752"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243581,180047017,71688238,"Łuczyńskiego (nż)","2604"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243160,180039710,71672918,"Polana Żywiecka (nż)","956"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243029,180028810,71663722,"Las Borkowski","750"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244147,180034355,71689049,"Cmentarz Borek Fałęcki (nż)","3139"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243630,180088849,71648999,"Kampus UJ","2690"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243631,180074981,71635343,"Chmieleniec","2691"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243142,180068343,71662874,"Torfowa","936"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242917,180083217,71676924,"Zachodnia","590"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243028,180026788,71730950,"Góra Borkowska (nż)","749"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243037,179991266,71711531,"Kąpielowa (nż)","759"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243038,179980050,71706040,"Opatkowice Wiadukt (nż)","760"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243016,179982400,71749269,"Wilga (nż)","737"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243691,180003145,71700527,"Judyma Szkoła","2792"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243036,180005166,71718621,"Judyma","758"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243021,180027009,71750524,"Jugowicka","742"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243019,180020334,71803331,"Myślenicka (nż)","740"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244142,179995766,71804974,"Warszewicza (nż)","3131"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244162,179975261,71765513,"Park Zdrojowy","3149"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243012,179965218,71789122,"Swoszowice Poczta","732"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244141,179974983,71780188,"Merkuriusza Polskiego","3130"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244139,179962571,71803463,"Sawiczewskich","3128"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244140,179978647,71808177,"Swoszowice Szkoła","3129"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243018,180002221,71792329,"Swoszowice Autostrada (nż)","739"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243020,180020953,71811624,"Halszki","741"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242931,180064239,71727416,"Rynek Fałęcki","616"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243025,180060156,71748900,"Solvay","746"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243027,180056568,71710839,"Żywiecka","748"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243026,180040675,71735839,"Borek Fałęcki","747"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243475,180044650,71773927,"Centrum JP II","2453"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244367,180036072,71800992,"Herberta (nż)","931"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243022,180035894,71814517,"Bujaka","743"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243024,180051626,71802942,"Przykopy (nż)","745"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244245,180049281,71822298,"Kurdwanów P+R","3176"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242932,180077414,71793636,"Fredry","617"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244558,180088344,71762472,"Łagiewniki ZUS","2821"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242933,180087107,71791707,"Sucha","618"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243193,180069553,71799910,"Turowicza (nż)","999"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243137,180066267,71825663,"Beskidzka","923"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243744,180106548,71306443,"Piekary Na Brzegu (nż)","2896"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244644,180119775,71339162,"Kryspinów Stopień Wodny (nż)","3371"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244059,180150588,71322660,"Bielany Obwodnica (nż)","3102"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242691,180154842,71349492,"Bielany","260"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243166,180116739,71384725,"Tor Kajakowy","966"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242696,180132777,71374032,"Bielańska (nż)","265"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242695,180140003,71393762,"Skalna (nż)","264"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242693,180155994,71392690,"Bielany Klasztor (nż)","262"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242694,180148130,71424057,"Wodociągi (nż)","263"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242692,180159516,71378604,"Bielany Szkoła","261"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244145,180171756,71375076,"Cmentarz Bielany (nż)","3137"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242618,180209999,71384900,"Zakamycze","155"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244030,180192240,71376588,"Obserwatorium (nż)","3068"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242619,180220399,71391603,"Kosmowskiej (nż)","156"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242921,180096881,71441123,"Kostrze Kapliczka (nż)","597"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243544,180098963,71450201,"Wały Wiślane (nż)","2564"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242922,180103273,71467714,"Kostrze OSP","599"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242927,180104784,71477456,"Kostrze Szkoła","605"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243546,180113523,71514068,"Fort Bodzów (nż)","2566"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243547,180122081,71552616,"Górka Pychowicka (nż)","2567"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244657,180148572,71539488,"Bodzów","3381"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244658,180134640,71558640,"Widłakowa (nż)","3382"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242609,180198311,71479418,"ZOO","146"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242699,180158820,71465278,"Bielańskie Skały (nż)","272"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242700,180162628,71498269,"Na Krępaku (nż)","273"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242701,180168503,71516263,"Zaskale (nż)","274"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244251,180166284,71537328,"Przegorzały Obwodnica (nż)","3182"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242702,180168417,71535462,"Przegorzały","275"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242608,180213114,71504560,"Baba Jaga (nż)","145"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242607,180223682,71535971,"Stara Wola","144"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242669,180275344,71309021,"Balice I (nż)","226"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243521,180231801,71346627,"Olszanica Ogródki Działkowe (nż)","2534"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243854,180243554,71366216,"Cmentarz Olszanica (nż)","3034"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242621,180245204,71381891,"Olszanica Kapliczka (nż)","158"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242620,180245540,71396916,"Leśmiana","157"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242616,180224244,71408470,"Rzepichy (nż)","153"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242615,180230094,71428025,"Chełmska (nż)","152"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242617,180247881,71413833,"Raczkiewicza (nż)","154"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242668,180315605,71344580,"Szczyglice Most (nż)","224"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243103,180338340,71310052,"Szczyglice Dom Kultury","871"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243865,180334446,71336236,"Szczyglice Autostrada","3045"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245010,180315348,71426869,"Cmentarz Mydlniki (nż)","3565"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244914,180329796,71382319,"Rząska Podkamycze","3487"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245008,180327245,71424855,"Mydlniki Wapiennik P+R","3560"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243512,180324969,71413247,"Mydlniki Granica Miasta (nż)","2525"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242611,180237418,71498678,"Kopalina (nż)","148"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243511,180290845,71539275,"Lindego (nż)","2524"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242614,180243202,71447900,"Chełm","151"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242612,180240385,71480663,"Zielony Dół","149"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244605,180250970,71493343,"Grabowa","3339"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242610,180234816,71515292,"Kasztanowa","147"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243163,180246625,71513161,"Panieńskich Skał","963"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242606,180241453,71529742,"Park Decjusza","143"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242605,180235005,71558745,"Sielanka","142"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242500,180299079,71459427,"Zakliki (nż)","7"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245009,180304585,71441670,"Wierzyńskiego","3564"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243757,180296434,71472344,"Godlewskiego","2909"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243756,180293067,71489961,"Młynówka SKA (nż)","2908"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242642,180348526,71443904,"Rząska UR (nż)","194"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242499,180291426,71514270,"UR Balicka","5"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245033,180293907,71552392,"Giełda Balicka (nż)","3588"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244119,180344284,71543391,"Cmentarz Bronowice (nż)","3108"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242556,180267743,71696440,"Biprostal","84"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242558,180226849,71653216,"Cichy Kącik","87"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242726,180191592,71700768,"Salwator","311"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243548,180126562,71588571,"Rodzinna","2568"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243549,180132602,71622402,"Pychowice","2569"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242916,180097588,71663761,"Ruczaj","589"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243628,180106513,71683450,"Norymberska","2688"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243629,180100503,71691277,"Rostworowskiego","2689"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243540,180137176,71636348,"Park 'Skały Twardowskiego' (nż)","2560"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242703,180169611,71580647,"Glinnik (nż)","276"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243510,180171340,71603122,"Benedyktowicza (nż)","2518"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244126,180173093,71629959,"Wodociągowa (nż)","3115"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242602,180218808,71609328,"Tondosa (nż)","139"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242603,180223583,71598223,"Lajkonika (nż)","140"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242683,180199148,71613776,"Kopiec Kościuszki","242"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244822,180215223,71620039,"Owcy-Orwicza","3465"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242725,180179392,71650952,"Malczewskiego (nż)","310"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244656,180191088,71641980,"Cmentarz Salwator","3107"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242914,180164579,71671798,"Os. Robotnicze","582"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242913,180167063,71683045,"Zielińskiego (nż)","581"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244823,180212823,71638348,"Piastowska","3466"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243593,180191762,71631135,"Aleja Waszyngtona (nż)","2625"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242730,180203084,71656493,"Przegon","316"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242731,180207941,71689795,"Instytut Reumatologii","317"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242905,180158724,71808116,"Korona","571"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242908,180164361,71720828,"Kapelanka","576"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243626,180110437,71729433,"Lipińskiego","2686"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243627,180114742,71711679,"Grota-Roweckiego","2687"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242930,180109261,71745241,"Borsucza","612"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242915,180122672,71733038,"Kobierzyńska","584"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243650,180110520,71753040,"TR","2724"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242909,180146782,71727980,"Słomiana","577"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242929,180117808,71778042,"Rzemieślnicza","611"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243136,180103248,71770176,"Łagiewniki","922"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243134,180105750,71802896,"Tischnera","919"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243386,180097765,71814615,"Puszkarska","1253"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242934,180111787,71817375,"Kamieńskiego Wiadukt (nż)","619"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242928,180131411,71785703,"Rondo Matecznego","610"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243696,180155183,71765353,"Ludwinów","2808"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242906,180149768,71795890,"Smolki","572"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242912,180178953,71713763,"Praska (nż)","580"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242736,180190131,71745339,"Konopnickiej","326"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244604,180173958,71746788,"Rondo Grunwaldzkie","3338"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242907,180168196,71731076,"Szwedzka","575"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243697,180165803,71758435,"Os. Podwawelskie","2812"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242910,180178763,71733414,"Bałuckiego (nż)","578"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242911,180187059,71737036,"Rynek Dębnicki","579"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242727,180192283,71718480,"Komorowskiego","313"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243503,180209300,71721900,"Cracovia Stadion","2501"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243502,180212188,71719373,"Cracovia Błonia","2500"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242733,180201816,71736433,"Jubilat","319"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244150,180211405,71730588,"Muzeum Narodowe","3141"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242735,180209520,71760888,"Filharmonia","322"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242734,180217284,71753529,"Uniwersytet Jagielloński","321"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242758,180185152,71789257,"Stradom","359"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242759,180172578,71796260,"Plac Wolnica","360"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244837,180185094,71822984,"św. Wawrzyńca","3471"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243463,180212760,71775360,"Plac Wszystkich Świętych","1360"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242756,180218232,71798472,"Poczta Główna","357"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242757,180206413,71806145,"Starowiślna","358"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242760,180194002,71813384,"Miodowa","362"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242761,180211324,71816415,"Hala Targowa","363"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244415,180283944,71632366,"Wesele","133"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242604,180229149,71582167,"Strzelnica","141"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243520,180258117,71607911,"Armii Krajowej","2533"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242601,180275840,71599104,"Zarzecze","137"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242557,180250920,71653324,"Miasteczko Studenckie AGH","85"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242560,180280440,71642592,"Bronowice","89"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244590,180271656,71662248,"Głowackiego","1049"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243517,180255923,71631135,"Przybyszewskiego","2530"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242554,180247684,71690076,"Kawiory","82"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242559,180266616,71677109,"Uniwersytet Pedagogiczny","88"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242599,180291708,71570304,"Bronowice Małe","135"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243472,180307692,71628645,"Fiszera","1375"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243473,180310754,71618395,"Radzikowskiego Osiedle","1376"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244805,180291250,71606335,"Bronowice SKA","3459"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242503,180315578,71606910,"Rondo Ofiar Katynia","12"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242504,180326550,71575807,"Katowicka","13"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242507,180339200,71577033,"Ojcowska Dworek","16"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242508,180352139,71580380,"Ojcowska Wągroda","17"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243690,180336360,71616527,"Stawowa","2791"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244113,180336816,71630604,"Chełmońskiego Pętla","3072"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244038,180329930,71649248,"Chełmońskiego Osiedle","3073"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244120,180322480,71685302,"Mehoffera","3109"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244246,180344880,71628840,"Piaskowa (nż)","3177"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243242,180302297,71648840,"Czerwieńskiego","1063"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243833,180300661,71661575,"Gnieźnieńska (nż)","2993"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243076,180318762,71633776,"Conrada","822"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242597,180299942,71672800,"Radzikowskiego","132"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242532,180311436,71672241,"Azory","54"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242533,180319712,71675798,"Różyckiego","55"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243590,180341431,71694212,"Jordanowska (nż)","2617"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242526,180354963,71683373,"Chabrowa","40"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242539,180299485,71762297,"Bratysławska","61"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243726,180243183,71702415,"Chopina","2878"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242553,180239668,71719756,"Czarnowiejska","81"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244122,180225592,71723469,"AGH / UR","3111"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242550,180241283,71743812,"Batorego","78"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242549,180229500,71757108,"Teatr Bagatela","77"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242551,180247251,71733117,"Plac Inwalidów","79"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242555,180263952,71711748,"Urzędnicza","83"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242561,180280361,71704037,"Mazowiecka","90"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242544,180283885,71720150,"Urząd Marszałkowski","67"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242548,180258805,71749580,"Grottgera","76"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245112,180258415,71747892,"Radio Kraków","3638"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244982,180271772,71752740,"Friedleina","3534"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242545,180278184,71734330,"Wrocławska","68"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243203,180257899,71815725,"Muzeum Armii Krajowej (nż)","1015"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243853,180238576,71784784,"Stary Kleparz","3032"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244181,180250842,71775131,"Pędzichów","72"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243240,180246648,71817575,"Dworzec Główny Wschód","1060"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242596,180232570,71803421,"Dworzec Główny","131"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244417,180232594,71804259,"Teatr Słowackiego","3242"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243585,180244278,71802763,"Dworzec Główny Zachód","2608"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242547,180256622,71798853,"Politechnika","73"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242546,180267208,71770508,"Nowy Kleparz","71"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243518,180274176,71808336,"Cmentarz Rakowicki Zachód (nż)","2531"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242588,180280850,71815209,"Biskupa Prandoty","122"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242530,180329516,71737622,"Opolska Kładka","48"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243513,180342640,71752863,"Pachońskiego","2526"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243514,180346936,71749712,"Prądnik Biały","2527"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243516,180347848,71753681,"Piaszczysta","2529"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244031,180296496,71698644,"Łobzów SKA","3069"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242535,180305793,71698793,"Stachiewicza","57"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242534,180315529,71702530,"Makowskiego","56"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244121,180310683,71722309,"Władysława Łokietka","3110"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243515,180319846,71724229,"Batalionu 'Skała' AK","2528"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242538,180301663,71735085,"Wybickiego","60"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243578,180305806,71750475,"Krowodrza Urzędy Skarbowe","2601"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242540,180320009,71734475,"Krowoderskich Zuchów","62"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242541,180318323,71752889,"Krowodrza Górka","63"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242527,180333105,71701951,"Skrajna (nż)","42"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245103,180325584,71712158,"Park Krowoderski","3636"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243102,180338887,71729031,"Prądnik Biały Zachód","870"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242528,180333596,71719889,"Wyki","43"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242543,180311308,71777895,"Lekarska","65"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244349,180318166,71779068,"Szpital Jana Pawła II","3203"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243855,180300745,71772782,"Pielęgniarek","3035"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243856,180290870,71773201,"Szpital Narutowicza","3036"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242525,180317823,71809959,"Imbramowska","38"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242529,180330686,71765762,"Pleszowska","47"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243401,180344358,71765123,"Białoprądnicka","1271"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242520,180345516,71811368,"Siewna Wiadukt","33"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242523,180335542,71801458,"Clepardia","36"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242524,180322619,71798500,"Mackiewicza","37"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243583,180353670,71826124,"Natansona","2606"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242890,180214788,72089353,"Na Załęczu","550"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242941,180095468,71855045,"Sławka (nż)","628"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243098,180200415,72088152,"Wiklinowa (nż)","866"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243796,179862484,71835259,"Wrząsowice Pod Skałą (nż)","2952"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243795,179853877,71868263,"Ochojno Dąbrówki","2951"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243794,179882212,71832365,"Wrząsowice Nad Wilgą","2950"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243096,179835077,71905566,"Ochojno Remiza","861"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243097,179845838,71897760,"Ochojno Kamieniec","863"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244160,179904677,71859820,"Zbydniowicka","3147"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244143,179927405,71837201,"Cmentarz Wróblowice","3133"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244159,179905672,71879999,"Golkowice Pętla","3146"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243151,179935212,71855432,"Kenara (nż)","945"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243565,179960940,71842788,"Fort Swoszowice (nż)","2587"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242987,179958348,71864172,"Landaua","694"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242988,179951400,71890848,"Rajsko","695"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244156,179901465,71927213,"Golkowice Kościół","3059"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243202,179911415,71952057,"Pod Lipą (nż)","1014"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243566,179947174,71919468,"Geologów (nż)","2588"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243567,179943074,71938847,"Malinowskiego (nż)","2589"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244968,179850503,71991742,"Podstolice Centrum","3529"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244969,179872161,71999435,"Podstolice Zamłynie (nż)","3530"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244158,179905538,72002083,"Grabówki Pętla","3145"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244157,179902816,71959681,"Golkowice Sklep","3144"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242989,179926727,71964210,"Soboniowice","699"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243568,179936500,71961700,"Drużbackiej","2590"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242990,179940759,71984681,"Krzemieniecka (nż)","702"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243569,179958244,72000810,"Żelazowskiego (nż)","2592"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242991,179945849,72008935,"Barycz (nż)","703"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243343,179951645,72087157,"Krzyszkowice Kasztanowa I (nż)","1192"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242998,180055077,71892147,"Piaski Nowe","716"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242994,180016920,71852400,"Os. Kurdwanów","710"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242995,180019030,71836575,"Stojałowskiego","711"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242993,180018542,71875689,"Kurdwanów Szkoła","709"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243572,180025593,71901783,"Cechowa (nż)","2595"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243831,180010307,71949773,"Hala Sportowa Piaski Wielkie (nż)","2991"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242985,180022188,71935716,"Piaski Wielkie","687"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242942,180072955,71840054,"Wola Duchacka","629"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243000,180046234,71839973,"Witosa","718"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243392,180028974,71833710,"Wysłouchów","1259"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242997,180050569,71879868,"Nowosądecka","715"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242999,180058763,71868413,"Karpińskiego","717"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243391,180028843,71860331,"Bojki","1258"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242996,180047473,71881550,"Tuchowska","714"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245075,180087887,71841888,"TW Hałas","3610"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245092,180072914,71839288,"Pszenna","3620"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243073,180086868,71852760,"PW","818"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243387,180085882,71850860,"Zajezdnia Wola Duchacka","1254"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245072,180088452,71851986,"TW Brama","3607"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245076,180091771,71847281,"TW Hala NB","3611"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243107,180079325,71872167,"Malborska Szkoła","880"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243551,180084706,71887577,"Sułkowskiego","2571"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242943,180082172,71935238,"Bieżanowska","630"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243573,180028774,71907353,"Łużycka (nż)","2596"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243574,180036859,71896059,"Bochenka (nż)","2597"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242944,180066635,71918451,"Dauna","632"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242946,180068763,71958227,"Wlotowa","634"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243570,179975380,71986702,"Szczegów","2593"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242992,179978990,71961109,"Kosocice","706"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243571,179996680,71959305,"Niebieska Autostrada (nż)","2594"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243125,180014563,71958627,"Przy Kuźni (nż)","904"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243561,180013451,71976603,"Hallera (nż)","2583"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243562,180013160,72013983,"Słona Woda","2584"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243344,179964827,72086694,"Krzyszkowice Kasztanowa II (nż)","1193"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243345,180150588,71322660,"Krzyszkowice Modrzewiowa (nż)","1194"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243563,180015585,72039003,"Kosocicka (nż)","2585"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243864,180022526,72040177,"Rżąka","3044"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243346,179994773,72087635,"Krzyszkowice Różana (nż)","1195"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244138,180024294,72063930,"Węzeł Wielicki (nż)","3127"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243666,180085424,71987663,"Prokocim Rynek","2746"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242981,180042734,71975328,"Wydział Farmaceutyczny UJ","683"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242982,180037324,71973070,"Prokocim UJ","684"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244670,180036576,72000973,"Szpital Uniwersytecki / Instytut Pediatr","3383"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244671,180033737,72013759,"Miejskie Centrum Opieki","3384"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242980,180051646,71992237,"Prokocim Szpital","682"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242979,180059654,72022863,"Teligi","681"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242945,180090403,71973084,"Prosta","633"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242947,180082368,72007020,"Młodzieży (nż)","636"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243135,180029769,72036114,"Czerwiakowskiego","920"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243564,180038243,72028848,"Jerzmanowskiego","2586"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243559,180046800,72056880,"Nowy Bieżanów Południe","2581"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243560,180057371,72042476,"Nowy Prokocim","2582"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242978,180057826,72067986,"Ćwiklińskiej","679"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243288,180029938,72066348,"Aleksandry","1122"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244137,180041461,72088075,"Cmentarz Bieżanów","3126"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244244,180053885,72084846,"Nowy Bieżanów P+R","3175"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243832,180078598,72025828,"Cmentarz Prokocim","2992"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242966,180075788,72062064,"Bieżanów Trafo","662"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242967,180067523,72089173,"Bieżanów Szkoła","663"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243704,179944771,72215373,"Wieliczka Goliana","2852"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243342,179956792,72121136,"Krzyszkowice Kościół (nż)","1191"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244931,179959301,72134199,"Krzyszkowice Os. Szymanowskiego (nż)","3503"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243700,179930552,72178014,"Wieliczka Sąd","2815"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243701,179947153,72166228,"Wieliczka Solne Miasto","2816"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243504,179952903,72181756,"Wieliczka Park","2503"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243322,179939338,72203091,"Wieliczka Kopalnia Soli","1167"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245098,179946252,72205272,"Wieliczka Centrum","3380"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243699,179932150,72202595,"Wieliczka Klaśnieńska (nż)","2814"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244729,179959320,72192855,"Wieliczka Klasztor","3430"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244430,179933544,72253476,"Wieliczka Gdowska","3246"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244728,179950931,72269776,"Wieliczka Miasto","3429"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244431,179928000,72280620,"Wieliczka Polna (nż)","3247"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244730,179943027,72232556,"Wieliczka Mediateka","3431"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244932,179938118,72251702,"Wieliczka Os. Asnyka","3504"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243121,179954487,72234352,"Wieliczka Cmentarz","898"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244421,179921808,72312372,"Lednica Górna Chorągwica (nż)","3216"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244422,179918028,72331992,"Lednica Górna (nż)","3217"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242970,180064256,72158378,"Sucharskiego (nż)","666"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244175,180078377,72155184,"Agatowa (nż)","1328"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243702,179963909,72113449,"Krzyszkowice Krzyszkowicka","2817"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244402,179976209,72152505,"Wieliczka Stacja Paliw","3235"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243649,179997074,72097138,"Wielicka Granica Miasta","2722"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245102,179991617,72212291,"Wieliczka Wygoda Kapliczka (nż)","3633"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245100,180009326,72199100,"Wieliczka Zbożowa (nż)","3631"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242969,180064944,72125352,"Bieżanów Pomnik","665"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242976,180036627,72098418,"Ks.Łaczka (nż)","675"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243582,180037700,72117027,"Świdzińskiego (nż)","2605"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245028,180053185,72101955,"Madejówka (nż)","3577"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245032,180055440,72102564,"Korepty (nż)","3581"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243083,180054397,72134058,"Lipowskiego (nż)","837"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243124,180034524,72150191,"Ślósarczyka","903"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243587,180042855,72145640,"Drożdżowa","2614"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244261,180041477,72132471,"Bieżanów Drożdżownia SKA","3183"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243082,180055013,72145977,"Dwór Czeczów","836"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244401,180053388,72151452,"Jędrzejczyka (nż)","3234"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244136,180064873,72102043,"Duża Góra (nż)","3125"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244134,180091871,72138166,"Magazynowa (nż)","3123"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242973,180029102,72190493,"Bieżanów Potrzask","670"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242975,180029157,72162085,"Bieżanów Kaim","672"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243556,180046647,72155144,"Weigla","2578"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242974,180049310,72175929,"Pruszyńskiego","671"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243555,180058024,72184172,"Złocieniowa","2577"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243067,180080316,72180576,"Złocień","811"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242972,180061295,72205794,"Jasieńskiego (nż)","668"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245101,179965797,72230217,"Wieliczka Czarnochowska Cmentarz (nż)","3632"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245099,179977394,72237664,"Wieliczka Orzechowa (nż)","3630"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245011,179972280,72308880,"Wieliczka Niepołomska Granica (nż)","3566"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245012,179973360,72311760,"Zabawa Granica (nż)","3567"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243498,179979111,72323201,"Zabawa (nż)","2495"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242971,180063132,72245724,"Bieżanów","667"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243651,180039141,72234328,"Bieżanów Granica Miasta","2725"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245029,180044073,72266256,"Czarnochowice Kokotowska (nż)","3578"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243314,180044125,72300316,"Kokotów I (nż)","1158"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243317,180049934,72341628,"Kokotów II (nż)","1162"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242575,180322352,71958855,"Os. Oświecenia","108"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244839,180230515,71953144,"TAURON Arena Kraków al. Pokoju","3473"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242937,180119952,71896464,"Dworcowa","623"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244227,180150716,71865197,"Podgórze SKA","3158"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245074,180093456,71839228,"TW Pantograf","3609"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243388,180107400,71832166,"Bonarka","1255"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245073,180094237,71853707,"TW Parking","3608"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242935,180104520,71846811,"Kamieńskiego","620"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242940,180098455,71877401,"Makowa","626"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242936,180142517,71868715,"Cmentarz Podgórski","621"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242939,180093775,71909475,"Malborska","625"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244870,180124232,71904985,"Dworzec Płaszów","820"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242938,180098984,71914330,"Kabel","624"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243722,180156110,71915776,"Strycharska","2874"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242896,180146866,71917250,"Gromadzka","560"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242897,180145956,71942571,"Lipska","561"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242904,180168066,71837323,"Plac Bohaterów Getta","570"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243152,180176828,71883950,"Klimeckiego","946"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243380,180183805,71864053,"Most Kotlarski (nż)","1239"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242762,180210312,71853984,"Rondo Grzegórzeckie","365"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242763,180216425,71880094,"Francesco Nullo","367"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242765,180219225,71925014,"Dąbie","370"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242902,180161915,71899587,"Kuklińskiego","567"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243721,180160981,71917525,"Płaszowska","2873"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242894,180174406,71935953,"Stoczniowców","558"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242895,180159775,71940897,"Saska","559"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242764,180214751,71917999,"Ofiar Dąbia","369"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242965,180131283,72022777,"Płk. Dąbka","660"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243715,180122690,71982922,"Bagry","2865"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244990,180124801,72000058,"Bagry Tężnia","3541"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242899,180140415,71974130,"Łanowa","563"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242900,180133973,71970489,"Płaszów Szkoła","564"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242901,180134875,71989530,"Motyla","565"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244967,180143150,71988228,"Węglarska","3532"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242898,180152436,71967662,"Szczecińska","562"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243395,180146451,71967260,"Rzebika","1262"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243550,180140441,72019359,"Surzyckiego","2570"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244574,180144730,72003196,"Mały Płaszów P+R","3310"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245078,180132260,72034553,"TP OC","3613"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245079,180132188,72026687,"TP ALB","3614"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243074,180129790,72041220,"PP","819"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243554,180126961,72042008,"Zajezdnia Płaszów","2576"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245077,180128254,72040518,"TP Brama","3612"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243553,180130649,72048493,"Biskupińska (nż)","2574"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243552,180135181,72070501,"Albatrosów","2573"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243084,180184264,72014580,"EC Łęg","838"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243539,180188876,71960059,"Koszykarska","2559"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242766,180202848,72000863,"Elektrociepłownia Kraków","374"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243101,180181310,72032185,"Szafrańska","869"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243085,180184709,72052963,"Zatyka (nż)","839"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243536,180204731,72053688,"Siwka (nż)","2556"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243086,180198704,72077200,"Isep (nż)","840"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243509,180212552,72087236,"Mobilis","2516"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244603,180211939,72074903,"Archiwum UMK (nż)","3337"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242570,180319052,71886463,"Olszecka","102"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242571,180307552,71893253,"Olsza II","103"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242572,180300126,71893901,"Miechowity","104"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242584,180287911,71907702,"Rondo Młyńskie","118"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242585,180280415,71892915,"Pilotów","119"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242591,180236319,71847244,"Rondo Mogilskie","125"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243320,180248708,71860070,"Brodowicza","1165"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242590,180265288,71852045,"Cmentarz Rakowicki","124"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242589,180286874,71853801,"Brogi","123"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242586,180271065,71875424,"Łukasiewicza","120"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242587,180265140,71877801,"Narzymskiego","121"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243860,180256082,71937842,"TAURON Arena Kraków Wieczysta","3040"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242594,180239246,71898731,"Cystersów","129"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242595,180243835,71918136,"Białucha","130"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243319,180282933,71915790,"Pszona","1164"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242581,180280642,71940367,"Ugorek","115"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243192,180269116,71926647,"Meissnera","995"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242582,180289058,71931178,"Spadochroniarzy","116"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242567,180352776,71859198,"Kuźnicy Kołłątajowskiej","98"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244124,180319127,71852482,"Dobrego Pasterza","3113"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242562,180305360,71832593,"Opolska Estakada","92"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243508,180296366,71833755,"Wileńska","2515"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242564,180306868,71862840,"Lublańska","94"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243519,180296491,71867270,"Bosaków","2532"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242565,180318367,71875463,"Majora","96"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242522,180334415,71842812,"Górka Narodowa","35"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243584,180353873,71847239,"Słomczyńskiego","2607"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243579,180331347,71872432,"Sudolska","2602"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242566,180336400,71871346,"Powstańców","97"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243311,180341797,71883047,"Powstańców Garaże","1155"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243066,180308900,71930500,"Bora-Komorowskiego","810"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243245,180322743,71905883,"Rondo Barei","1066"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244146,180322188,71915876,"Słoneckiego","3138"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242583,180293706,71919191,"Akacjowa","117"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244285,180312336,71950932,"Jurczaka (nż)","3190"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242569,180335817,71912013,"Strzelców","101"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242568,180345255,71907572,"Prądnik Czerwony","100"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243198,180324278,71936141,"Park Wodny","1004"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243295,180335678,71956404,"Marchołta","1130"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243861,180244335,72025221,"Rondo 308. Dywizjonu","3041"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242580,180266423,71991107,"AWF","113"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244838,180236164,71986554,"M1 al. Pokoju","3472"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243720,180244941,71959501,"TAURON Arena Kraków","2872"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243658,180230823,72014483,"M1 Nowohucka","2736"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242579,180265151,72012244,"Stella-Sawickiego","112"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243528,180231030,72051289,"Sołtysowska Zakłady","2545"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242794,180250478,72045366,"Centralna","409"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243527,180240850,72044066,"Sołtysowska (nż)","2544"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243529,180231217,72068396,"Sołtysowska Osiedle","2546"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243321,180227224,72079929,"Sikorki","1166"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242792,180265659,72044923,"Czyżyny","407"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243165,180269378,72053483,"Czyżyny Dworzec","965"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242791,180274664,72026288,"Medweckiego","406"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244128,180281232,72053604,"Dąbrowskiej","3117"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242793,180265716,72059400,"Rondo Czyżyńskie","408"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243099,180275163,72077706,"Bieńczycka","867"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244125,180314139,71982206,"Os. Akademickie PK","3114"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242578,180305532,71998596,"Os. Dywizjonu 303","111"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242577,180317690,72005455,"Wiślicka","110"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244144,180342719,71964007,"Kurzei","3136"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242767,180345289,71986404,"Mistrzejowice","375"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242768,180338339,72016713,"Os. Złotego Wieku","377"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242776,180324850,72015411,"Cedyńska","385"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242775,180334553,72023281,"Łęczycka","384"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243522,180339851,72002071,"Miśnieńska","2538"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242778,180343422,72062946,"Kupały","387"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242779,180326443,72060268,"Dunikowskiego","388"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242780,180311365,72033949,"Os. Strusia","390"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242781,180311854,72049875,"Os. Na Lotnisku","391"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242782,180308769,72069944,"DH Wanda","392"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244795,180297485,72060224,"Os. Kościuszkowskie","3445"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242777,180329141,72036324,"Os. Kalinowe","386"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242774,180339109,72040386,"Rondo Piastowskie","383"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242770,180352624,72047149,"Piasta Kołodzieja","379"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242773,180346248,72051213,"Kleeberga","382"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243077,180333636,72077828,"Szpital Rydygiera","827"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243523,180327283,72078653,"Rondo Hipokratesa","2539"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242772,180350899,72069300,"Kruszwicka","381"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243659,180341808,72076851,"Os. Mistrzejowice Nowe","2737"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242960,180160200,72165925,"Szparagowa","651"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244135,180137664,72156240,"Półłanki","3124"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244133,180102298,72142008,"Danalówka (nż)","3122"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244132,180121808,72152286,"Bazarowa","3121"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242964,180138362,72096602,"Jeżowa","655"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242963,180149778,72103305,"Rybitwy Sklep (nż)","654"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242962,180154279,72128405,"Rybitwy Dom Kultury","653"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244348,180136656,72192276,"Śliwiaka Zakłady","2517"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244710,180118872,72183924,"Domagały Magazyny (nż)","3412"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242955,180118434,72184313,"Domagały (nż)","646"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242954,180116606,72204603,"Nad Drwiną","645"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244347,180135036,72214164,"Śliwiaka (nż)","1374"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243661,180154114,72188758,"Ks.Targosza (nż)","2739"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243689,180189225,72136288,"Lasek Łęgowski (nż)","2790"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242891,180206990,72111249,"Habina (nż)","552"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242892,180194509,72126722,"Odmętowa","553"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242953,180170789,72218150,"Podwierzbie","644"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242961,180165791,72157810,"Rybitwy Rozjazd (nż)","652"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244654,180165528,72171216,"Cmentarz Półłanki","2956"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242893,180194482,72163162,"Lesisko","554"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243537,180195453,72178199,"Lasek Mogilski","2557"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243538,180214366,72180867,"Stare Wiślisko","2558"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244185,180160485,72238403,"Husarska (nż)","3157"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242951,180148817,72224384,"Przewóz","641"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242950,180145858,72263961,"Łutnia","640"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244575,180113293,72341995,"Brzegi Pętla","3311"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244934,180119891,72338574,"Brzegi Grobla","3509"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244606,180125708,72314827,"Brzegi Użytek Ekologiczny (nż)","3340"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244247,180213912,72285192,"Giedroycia ZTPO (nż)","3178"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244248,180191311,72221891,"Zakarnie (nż)","3179"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244249,180204480,72244800,"Kępa (nż)","3180"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242952,180171162,72238840,"Wrobela","643"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243525,180321687,72151564,"Makuszyńskiego (nż)","2542"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243665,180284702,72094532,"Rondo Kocmyrzowskie im. Ks. Gorzelanego","2745"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242796,180271339,72124531,"Aleja Przyjaźni","415"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243664,180263243,72130673,"Plac Centralny im. R.Reagana","2744"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242795,180260959,72102189,"Os. Kolorowe","413"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242799,180277986,72110415,"Os. Zgody","418"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242797,180270978,72138307,"Aleja Róż","416"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242798,180280935,72141196,"Żeromskiego","417"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242811,180274106,72186046,"Bulwarowa (nż)","432"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242805,180239530,72167563,"Szpital Żeromskiego","425"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242806,180231082,72187845,"Klasztor Cystersów","427"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243530,180238039,72185172,"Sieroszewskiego (nż)","2547"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242804,180250400,72166910,"Os. Na Skarpie","424"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242807,180249823,72175024,"Os. Wandy","428"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242808,180247779,72181122,"Klasztorna","429"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243580,180255972,72192535,"Bulwarowa Ogródki Działkowe (nż)","2603"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245053,180243018,72211991,"Suche Stawy","2548"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242803,180269283,72171523,"Struga","423"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242810,180263854,72180747,"Orkana","431"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242802,180286053,72165586,"Os. Zielone","422"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242809,180261921,72192205,"Os. Willowe","430"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244167,180287795,72204752,"Krzesławice Młyn (nż)","3134"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242784,180336208,72117489,"Nowolipki (nż)","395"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242800,180291991,72122218,"Teatr Ludowy","420"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242788,180302688,72107412,"Arka","403"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242787,180316072,72099962,"Os. Jagiellońskie","402"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243524,180321542,72144722,"Nad Dłubnią","2541"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242801,180291953,72148696,"Os. Górali","421"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243857,180307836,72141056,"Cienista","3037"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244127,180336170,72097938,"Fatimska (nż)","3116"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243072,180327730,72114120,"PB","817"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245081,180331549,72119383,"TB Parking","3616"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245082,180334933,72117767,"TB Hala NB","3617"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245083,180334742,72111283,"TB Solaris","3618"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245084,180332356,72113735,"TB Magazyn","3619"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245080,180329634,72115463,"TB Brama","3615"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242786,180328517,72118202,"Zajezdnia Bieńczyce","397"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244281,180339028,72127806,"Petőfiego (nż)","3191"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244129,180342074,72144431,"Władysława Jagiełły","3118"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243526,180311774,72170460,"Wańkowicza","2543"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243872,180307411,72176767,"Krasnowolskiego (nż)","998"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244168,180303211,72195425,"PCK","3135"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242812,180317171,72201629,"Wiadukty (nż)","434"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243594,180341825,72169224,"Jana Kazimierza","2626"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243145,180352358,72180643,"Os. Na Stoku","939"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243591,180344949,72182849,"Leszka Białego","2619"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242814,180335497,72202985,"Os. Na Stoku Szkoła","436"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242813,180325670,72213883,"Darwina","435"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242815,180350565,72196679,"Architektów","437"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243235,180235255,72252974,"Fort Mogiła","1051"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245052,180243357,72227648,"Bardosa (nż)","449"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242830,180284760,72235080,"Kombinat","459"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244243,180230832,72293508,"Giedroycia (nż)","3174"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242825,180244429,72327211,"Brama nr 5 (nż)","453"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242833,180328356,72260604,"Lubocza PKP (nż)","467"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242831,180316692,72225252,"Elektromontaż (nż)","464"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242832,180303426,72236610,"Zajezdnia Nowa Huta","465"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244111,180315648,72286128,"Agencja Kraków Wschód (nż)","462"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244112,180322884,72301572,"Walcownia","463"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244109,180302580,72258372,"Mrozowa (nż)","460"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244110,180306648,72267444,"Blokowa (nż)","461"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243830,180324753,72231397,"Cmentarz Grębałów Południe (nż)","2989"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242821,180327830,72252893,"Cmentarz Grębałów","448"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242817,180343577,72232675,"Wzgórza Krzesławickie","442"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242834,180331470,72266468,"Lubocza Bugaj (nż)","468"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242835,180341366,72300942,"Lubocza Przychodnia","469"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242836,180346356,72336888,"Lubocza Szkoła","470"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244426,179887968,72479520,"Biskupice II (nż)","3221"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244423,179913060,72357048,"Tomaszkowice","3218"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244424,179904060,72400896,"Przebieczany","3219"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244425,179900928,72445968,"Biskupice I (nż)","3220"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244429,179846316,72529452,"Trąbki P+R","3245"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244428,179848728,72527652,"Trąbki OSP","3223"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244427,179867376,72512064,"Trąbki I (nż)","3222"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243439,180012209,72458957,"Ochmanów Oknoplast (nż)","1313"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243652,180008844,72447705,"Ochmanów Otaczarnia (nż)","2727"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244484,180053640,72414720,"Węgrzce Wielkie Pętla","3270"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245030,180053640,72398520,"Węgrzce Wielkie OSP (nż)","3579"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245031,180052920,72381240,"Węgrzce Wielkie Gościniec (nż)","3580"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243499,179995637,72367271,"Mała Wieś (nż)","2496"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243500,179997450,72396649,"Sułków (nż)","2497"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243437,180048385,72592403,"Podłęże Straż (nż)","1310"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243653,180048599,72582566,"Podłęże Przedszkole (nż)","2728"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243432,180035050,72525829,"Zakrzów (nż)","1305"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243438,180039224,72547746,"Podłęże Balachówka (nż)","1311"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244963,180083509,72840956,"Niepołomice Puszcza","3526"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243433,180084240,72715320,"Niepołomice Boryczów (nż)","1306"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243434,180069840,72692280,"Niepołomice Wielicka (nż)","1307"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243435,180056744,72638573,"Staniątki Wiadukt (nż)","1308"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243436,180051489,72617096,"Podłęże Centrum (nż)","1309"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244935,180132808,72367392,"Brzegi Dom Kultury (nż)","3510"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244936,180138074,72390740,"Brzegi Granica (nż)","3511"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243420,180139368,72402364,"Grabie Szczurów (nż)","1292"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243421,180143562,72480916,"Grabie Dom Kultury (nż)","1293"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242887,180173362,72360411,"Kujawy","544"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243623,180190033,72366407,"Oczyszczalnia Ścieków 'Kujawy' (nż)","2655"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243625,180210462,72368387,"Dymarek (nż)","2657"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242888,180217640,72388935,"Karowa (nż)","546"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242873,180176374,72439381,"Chałupki","525"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243618,180171407,72460966,"Truskawkowa (nż)","2650"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242874,180185209,72457996,"Kąkolowa (nż)","527"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243619,180196745,72450372,"Chałupki Górne (nż)","2651"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243620,180209989,72465885,"Branice Ośrodek Zdrowia (nż)","2652"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243423,180146191,72502459,"Grabie Kościół","1295"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243424,180139128,72541186,"Niepołomice Podgrabie (nż)","1296"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243425,180147295,72598498,"Niepołomice Pasternik (nż)","1297"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242876,180216997,72515639,"Branice Szkoła","531"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242875,180213138,72492696,"Branice (nż)","530"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245128,180176724,72560376,"Kępa Grabska","3637"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242877,180165951,72589892,"Przylasek Rusiecki","532"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243621,180181242,72591625,"Ciekowiec (nż)","2653"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243622,180207276,72571403,"Rzepakowa Świetlica (nż)","2654"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243848,180220541,72552953,"Rzepakowa (nż)","3009"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244991,180194032,72580735,"Przylasek Rusiecki Kąpielisko","3542"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243624,180251873,72366185,"Nadbrzezie (nż)","2656"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242826,180238900,72394574,"Gwarecka (nż)","455"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242827,180250888,72388221,"Suchy Jar (nż)","456"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242828,180258102,72376449,"Koksochemia (nż)","457"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242829,180262080,72426240,"Pleszów","458"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242863,180265090,72456421,"Zakład Przeróbki (nż)","511"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242837,180339620,72360724,"Lubocza Trafo (nż)","471"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242850,180347361,72429476,"Wadowska (nż)","486"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242849,180350050,72447098,"Wadów","485"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243535,180333900,72479801,"Wadów Glinik (nż)","2554"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244929,180349438,72463702,"Wadów Os. Kolejowe","3501"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242865,180230031,72509259,"Branice Na Dole (nż)","513"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242864,180247142,72503272,"Branice Pagórek (nż)","512"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243614,180270349,72503867,"Szymańskiego (nż)","2646"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242862,180274389,72535447,"Igołomska (nż)","510"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243849,180235519,72550685,"Prawocheńskiego (nż)","3010"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242868,180234088,72605926,"Drożyska (nż)","518"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243615,180251001,72584159,"Wyciąska (nż)","2647"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242867,180247568,72601209,"Podstawie (nż)","517"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242866,180259506,72570320,"Wyciąże Sklep (nż)","515"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244846,180277657,72554896,"Jeziorko (nż)","3477"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242861,180278763,72554170,"Wyciąże (nż)","508"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242860,180285946,72577073,"Janówka (nż)","506"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243613,180280142,72586059,"Wyciąże Zachód (nż)","2645"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244131,180305831,72539543,"Cmentarz Ruszcza (nż)","3120"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242870,180312545,72526967,"Ruszcza","520"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242853,180334044,72501876,"Wadów Działki (nż)","492"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244492,180336597,72528887,"Spławy","3276"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242859,180303855,72597096,"Błonia Kościelnickie (nż)","505"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242879,180186188,72746894,"Wolica Most","536"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243428,180122106,72677832,"Niepołomice Diesla (nż)","1300"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243426,180140432,72622831,"Niepołomice Kątek (nż)","1298"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243427,180134020,72648036,"Niepołomice Poręby (nż)","1299"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243429,180120435,72693550,"Niepołomice Wodna (nż)","1301"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243431,180104572,72742863,"Niepołomice Kościuszki (nż)","1303"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243430,180111951,72712484,"Niepołomice Płaszowska I (nż)","1302"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242886,180212550,72636336,"Przylasek Wyciąski (nż)","543"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242885,180205733,72652338,"Siejówka (nż)","542"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242884,180204018,72672504,"Bartnicza (nż)","541"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244718,180201071,72696323,"Wolica Szkoła (nż)","3427"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242880,180203207,72728194,"Wolica Las","537"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242881,180198102,72728497,"Wolica Kościół (nż)","538"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243164,180159120,72763920,"Niepołomice Kolejowa (nż)","964"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243404,180139176,72777564,"Niepołomice Dworzec","1276"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243405,180119884,72779195,"Niepołomice Rynek","1277"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243406,180146835,72804106,"Niepołomice Batorego (nż)","1278"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243407,180151408,72826744,"Niepołomice Batorego II (nż)","1279"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243417,180157126,72864147,"Niepołomice Jazy (nż)","1289"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242872,180289230,72665678,"Cło","522"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242871,180284693,72620187,"Wyciąże Wiadukt (nż)","521"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242882,180225119,72701147,"Wolica (nż)","539"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243617,180244764,72682952,"Zakępie (nż)","2649"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243616,180261256,72682656,"Brzeska (nż)","2648"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243612,180323926,72631384,"Kościelniki","2644"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242858,180341657,72626839,"Zabłocie Kościelnickie (nż)","503"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243408,180191486,72931463,"Wola Batorska Ruskie (nż)","1280"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243418,180168305,72886192,"Niepołomice Jazy II (nż)","1290"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243409,180189885,72956538,"Wola Batorska Żwirownia (nż)","1281"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243410,180190973,72996677,"Wola Batorska Kościół (nż)","1282"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243412,180190424,73062182,"Wola Batorska Borek (nż)","1284"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243411,180189716,73043805,"Wola Batorska Cmentarz (nż)","1283"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243413,180211589,73075779,"Wola Batorska Mikoniowiec (nż)","1285"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243414,180231454,73088158,"Wola Batorska Zamoglice (nż)","1286"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243416,180288360,73269360,"Chobot Leśniczówka","1288"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243402,180264960,73247760,"Chobot (nż)","1274"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243419,180254160,73208880,"Wola Zabierzowska (nż)","1291"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243415,180245140,73156880,"Zabierzów Bocheński Centrum (nż)","1287"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245036,180368208,70514136,"Rudno Zamek","3589"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245040,180639594,70700652,"Czerna Las","3590"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243979,180484488,70696908,"Krzeszowice Krakowska Muzeum (nż)","2923"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244435,180473400,70693560,"Krzeszowice Dworzec Autobusowy","3250"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244961,180474956,70733146,"Krzeszowice Wiadukt (nż)","3525"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244960,180462032,70765940,"Nawojowa Góra Gwoździec (nż)","3524"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245041,180582250,70675395,"Czerna Klasztor","3591"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244955,180406237,71043422,"Kochanów Sowiarka (nż)","3519"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244705,180486536,70894644,"Pisary Spacerowa (nż)","3420"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244956,180409762,70909835,"Młynka Kalwaryjska (nż)","3520"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242497,180403227,70892538,"Młynka Pętla","1"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244959,180450520,70820684,"Nawojowa Góra Zagrody (nż)","3523"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243378,180464760,70891200,"Pisary Dwór (nż)","1236"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244916,180443779,70851002,"Nawojowa Góra Pisarska (nż)","3489"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244958,180429436,70892022,"Młynka Dolna (nż)","3522"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244706,180452102,70908252,"Pisary Akacjowa (nż)","3421"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243377,180369192,70978742,"Nielepice Stadnina (nż)","1235"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244709,180384056,70954362,"Nielepice Pętla","3424"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243243,180411844,70923675,"Rudawa Młyn (nż)","1064"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243770,180412074,70961117,"Rudawa Stadion (nż)","2924"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244707,180448833,70927468,"Pisary Mleczna (nż)","3422"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243239,180431425,70968673,"Rudawa PKP","1059"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243224,180444319,70946399,"Rudawa Szkoła","1039"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244704,180441291,70961985,"Rudawa Rynek","3419"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244703,180458332,70995542,"Rudawa Osiedle (nż)","3418"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243710,180504720,70895880,"Pisary Kasztanowa (nż)","2860"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244966,180521599,70896386,"Pisary Dąbrówki (nż)","3531"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244917,180507719,70946052,"Rudawa 21 Lipca (nż)","3490"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243218,180533695,70953548,"Radwanowice Pętla","1032"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244359,180520098,70949010,"Radwanowice Krzeszowicka (nż)","3208"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244701,180496304,71171271,"Więckowice Ogrodowa (nż)","3416"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244708,180377753,71095984,"Kleszczów Centrum","3423"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244954,180413500,71092832,"Kochanów Centrum","3518"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243714,180370494,71110542,"Kleszczów Pod Lipką (nż)","2864"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244953,180418294,71145417,"Zabierzów Wrzosowa (nż)","3517"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244434,180421028,71280185,"Zabierzów Rynek","3249"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244952,180416634,71250172,"Zabierzów Kamieniołom","3516"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244951,180406221,71290090,"Zabierzów Centrum","3515"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242657,180422105,71189212,"Zabierzów Ośrodek Zdrowia","213"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242651,180421317,71217842,"Zabierzów Piaski (nż)","203"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243768,180482339,71199633,"Więckowice Lipka (nż)","2921"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242652,180424762,71282760,"Zabierzów PKP","204"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243162,180431049,71285721,"Zabierzów Młyn","961"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243767,180475554,71249545,"Bolechowice Zielona Mała (nż)","2920"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243763,180457172,71293327,"Bolechowice Zielona (nż)","2915"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243215,180488244,71062984,"Brzezinka Skrzyżowanie","1029"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244702,180496599,71058986,"Brzezinka Kościół","3417"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243213,180497837,71092451,"Brzezinka Zarzecze (nż)","1027"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244358,180533225,71111145,"Kobylany Dworska (nż)","3207"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243244,180534725,71131245,"Kobylany Remiza (nż)","1065"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244355,180527125,71146609,"Kobylany Kwiatowa","3204"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243637,180571082,71090635,"Będkowice Pętla","2700"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243718,180591375,71101739,"Będkowice Długa (nż)","2869"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245000,180603476,71107351,"Będkowice Rynek","3551"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244356,180549000,71186400,"Karniowice Pętla","3205"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244357,180537756,71205307,"Karniowice Górki (nż)","3206"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243765,180512775,71268197,"Bolechowice Drogi (nż)","2917"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243764,180486618,71276950,"Bolechowice Krzewiny (nż)","2916"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244698,180529727,71250080,"Bolechowice Centrum","3413"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242655,180574604,71265519,"Zelków Staw","210"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242654,180554112,71278272,"Zelków Wola (nż)","209"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243766,180580729,71281135,"Zelków Spławy (nż)","2918"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244338,180581632,71300230,"Zelków Kościuszki (nż)","3200"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243846,180618402,71098235,"Będkowice Cmentarz (nż)","3007"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243181,180633022,71115842,"Będkowice Borynia (nż)","981"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243180,180644367,71147144,"Będkowice Kawiory (nż)","980"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243179,180650540,71161474,"Będkowice Mosurowa (nż)","979"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243175,180682791,71268462,"Czajowice (nż)","975"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243177,180647809,71228926,"Bębło Łabajowa (nż)","977"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243178,180659576,71199031,"Bębło Lasek (nż)","978"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243176,180664509,71252436,"Bębło Stara Wieś (nż)","976"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244023,180367632,72356364,"Łuczanowicka (nż)","3066"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243440,180882720,72306360,"Słomniki Osiedle","1315"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244997,180564631,71435729,"Wielka Wieś Szkoła (nż)","3548"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244949,180390496,71338521,"Zabierzów Kmity","3513"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244950,180403942,71316613,"Zabierzów Willowa","3514"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244439,180383965,71366800,"Zabierzów Eximius Park","3248"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242645,180390130,71406355,"Modlniczka Mała","197"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242644,180379927,71408992,"Rząska Autostrada (nż)","196"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244337,180356946,71412621,"Rząska Szkoła","3199"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244948,180392815,71373856,"Zabierzów Rzemieślnicza (nż)","3512"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243204,180420358,71383408,"Brzezie Działki (nż)","1016"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243230,180394144,71422072,"Modlniczka Trzcie (nż)","1046"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243232,180450207,71332017,"Brzezie Kulka (nż)","1048"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243209,180439027,71360291,"Brzezie Narodowe (nż)","1021"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244360,180465401,71358470,"Brzezie Kluczwody (nż)","3209"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243867,180476838,71357902,"Brzezie Kościół (nż)","3049"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245007,180485659,71393374,"Tomaszowice Spacerowa (nż)","3558"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244335,180485064,71412840,"Tomaszowice Kolberga (nż)","3167"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245006,180483828,71430859,"Tomaszowice Dwór","3557"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244655,180366646,71500820,"Rząska Cmentarz (nż)","2990"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244436,180381755,71462733,"Modlniczka Leroy Merlin (nż)","3251"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242641,180358699,71480077,"Rząska Jednostka Wojskowa (nż)","193"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245001,180414530,71485764,"Modlniczka Willowa (nż)","3552"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243461,180415346,71523998,"Modlniczka Poligon (nż)","1353"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244992,180405744,71542945,"Modlnica Podchruście (nż)","3543"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243588,180391291,71562546,"Gaik (nż)","2615"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245002,180431422,71477326,"Modlniczka Dworska","3553"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245004,180480779,71480214,"Modlnica Kościelna (nż)","3555"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243185,180448275,71523464,"Modlnica Skrzyżowanie (nż)","985"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245003,180470551,71507228,"Modlnica Szkoła","3554"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244993,180461764,71533265,"Modlnica Kolonia (nż)","3544"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243205,180488876,71353141,"Ujazd Pętla","1017"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244336,180581222,71321675,"Zelków Pętla","3198"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244998,180607449,71374873,"Biały Kościół Dolina Kluczwody (nż)","3549"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243172,180566615,71431962,"Wielka Wieś (nż)","972"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244802,180597960,71542080,"Grębynice Remiza (nż)","3456"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244803,180603360,71524080,"Grębynice Parkowa (nż)","3457"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245005,180486973,71454286,"Tomaszowice Zacisze (nż)","3556"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244996,180541961,71474273,"Szyce Wesoła (nż)","3547"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243330,180545760,71535960,"Giebułtów Morgi","1177"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243460,180531000,71550720,"Giebułtów Zbiornik (nż)","1352"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244924,180497558,71530804,"Giebułtów Świętego Idziego (nż)","3496"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244995,180514759,71504653,"Szyce Spacerowa (nż)","3546"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244994,180490896,71515673,"Modlnica Komora (nż)","3545"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243329,180518400,71562960,"Giebułtów Plac Zabaw (nż)","1176"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244923,180488637,71537238,"Giebułtów Zielone Wzgórze (nż)","3495"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242513,180405595,71634112,"Tonie Skrzyżowanie","23"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242511,180395657,71592343,"Tonie","21"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242509,180362793,71582629,"Ojcowska","18"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242510,180381704,71568432,"Bronowice Wielkie","19"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243589,180402562,71615675,"Na Budzyniu","2616"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244806,180363025,71676115,"Starego Wiarusa","3460"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245056,180362619,71691252,"Pękowicka","3575"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243694,180375378,71664050,"Poziomkowa","2806"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242512,180390005,71649367,"Gospodarska","22"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242514,180416571,71643979,"Tonie Kąty (nż)","25"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245054,180408347,71688054,"Zielonki Długopolska","3573"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244921,180450559,71585003,"Modlnica Cicha (nż)","3493"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243190,180421076,71619292,"Tonie Gliniki (nż)","990"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244922,180463957,71570797,"Giebułtów Osiedle Polana (nż)","3494"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242633,180484560,71659800,"Trojanowice Strugi (nż)","183"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244834,180461430,71632271,"Pękowice Ojcowska","3440"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244840,180448837,71645638,"Pękowice Fort","3474"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242632,180387440,71727690,"Zielonki Skrzyżowanie","182"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245055,180391575,71697246,"Zielonki Na Popielówkę","3574"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245057,180374517,71706686,"Zielonki Słoneczna","3576"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243667,180369412,71739266,"Glogera (nż)","2751"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243668,180375378,71735241,"Glogera Granica Miasta (nż)","2752"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242516,180390578,71795117,"Witkowice","29"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242519,180356178,71810618,"Siewna","32"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242518,180368749,71805732,"Dożynkowa","31"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242517,180377787,71800222,"Witkowice Nowe","30"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244920,180425624,71719879,"Zielonki Urząd Gminy","3492"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242631,180425249,71726872,"Zielonki UG","181"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242630,180445766,71712950,"Zielonki Transformator","180"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243687,180441133,71759664,"Zielonki Galicyjska (nż)","2784"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243677,180484920,71722080,"Garlica Murowana Kasztany (nż)","2770"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244625,180475988,71708486,"Zielonki Jaskinia Zielonkowska (nż)","3358"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242628,180465966,71701369,"Zielonki Rozjazd","177"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242515,180426128,71789796,"Os. Marszowiec","28"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243686,180452520,71792640,"Bibice Cmentarz (nż)","2783"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243819,180444625,71783219,"Zielonki Marszowiec Pętla","2978"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242638,180588600,71636760,"Przybysławice I (nż)","190"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242639,180565920,71622720,"Przybysławice II","191"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243254,180574560,71628480,"Przybysławice III (nż)","1084"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243811,180560160,71580240,"Korzkiew Botoja (nż)","2970"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243812,180582120,71571960,"Korzkiew Zamek (nż)","2971"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244925,180509760,71582760,"Giebułtów Kościół","3497"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242635,180538812,71614260,"Januszowice","185"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242629,180507960,71678880,"Trojanowice Pętla","178"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243818,180494280,71686800,"Trojanowice Krakowska (nż)","2977"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242634,180500724,71637264,"Trojanowice","184"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243597,180563400,71707680,"Garliczka Chochół (nż)","2629"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243598,180583200,71724240,"Owczary Górna (nż)","2630"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243600,180610200,71732520,"Owczary Gajowa (nż)","2632"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244611,180519020,71729574,"Garlica Duchowna Kapliczka (nż)","3344"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244612,180506576,71738511,"Garlica Murowana Plac Zabaw","3345"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243596,180535818,71719515,"Garlica Duchowna Rozjazd","2628"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244610,180541766,71752993,"Wola Zachariaszowska Szkoła","3343"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244623,180571680,71791200,"Michałowice Las Michałowski","3356"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244627,180560122,71771193,"Wola Zachariaszowska Słoneczna","3360"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244622,180558000,71813160,"Michałowice Jana Pawła II (nż)","3355"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244609,180585322,71769876,"Górna Wieś Pętla","3342"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243806,180612583,71785411,"Michałowice Michałówka (nż)","2965"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245047,180606461,71824149,"Michałowice Na Stoku (nż)","3597"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243308,180625320,71509680,"Grębynice Mogiełki (nż)","1150"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243309,180650520,71481240,"Maszyce Skrzyżowanie (nż)","1152"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243328,180644760,71501400,"Maszyce Remiza (nż)","1174"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243813,180672120,71476560,"Smardzowice Wołówka (nż)","2972"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244800,180690840,71473680,"Smardzowice Remiza","3454"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244804,180640080,71508240,"Maszyce Sadzawka (nż)","3458"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245038,180653345,71324040,"Biały Kościół Murownia Pętla","3559"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244999,180643239,71329089,"Biały Kościół Murownia (nż)","3550"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244964,180766300,71392622,"Ojców Zamek","3527"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244965,180785657,71401976,"Ojców Złota Góra","3528"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243809,180627120,71687520,"Owczary Pętla","2968"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244488,180631800,71627040,"Brzozówka Oskarówka","3271"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244489,180619200,71633520,"Brzozówka Ośrodek","3272"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244621,180761040,71818200,"Krasieniec Zakupny Kapliczka","3354"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244801,180639000,71669520,"Owczary Długa (nż)","3455"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243807,180650520,71757360,"Narama Remiza","2966"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244624,180619560,71709840,"Owczary Spacerowa (nż)","3357"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243804,180625155,71794063,"Michałowice Gościniec (nż)","2963"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244615,180649388,71800120,"Narama Graniczna Sklep (nż)","3348"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244616,180669385,71797197,"Narama Księża (nż)","3349"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244617,180684859,71790195,"Krasieniec Stary Skrzyżowanie (nż)","3350"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244618,180705300,71779778,"Krasieniec Stary Kopalina (nż)","3351"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244626,180746640,71776440,"Krasieniec Zakupny Pod Lipą","3359"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244619,180723336,71780703,"Krasieniec Zakupny Remiza (nż)","3352"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243250,180420719,71884206,"Węgrzce Centrum Medyczne (nż)","1074"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243692,180365400,71871120,"Banacha","2796"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243693,180359570,71872025,"Górka Narodowa Wschód","2797"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243601,180381242,71873241,"Belwederczyków (nż)","2633"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244608,180395941,71877326,"Węgrzce Trzecia Góra (nż)","3341"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242737,180410758,71879274,"Węgrzce Starostwo Powiatowe","327"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243120,180368173,71944468,"Cmentarz Batowice","897"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243636,180368951,71952554,"Cmentarz Batowice Wschód (nż)","2699"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244794,180386309,71947273,"Węgrzce Sudół (nż)","3444"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243249,180411345,71906729,"Węgrzce Magiczne Wzgórze (nż)","1073"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244793,180392483,71931621,"Węgrzce B4 (nż)","3443"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243801,180437166,71851100,"Bibice Kościuszki","2960"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245042,180431852,71881731,"Węgrzce A1","3592"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243802,180455040,71833680,"Bibice Rynek","2961"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244628,180477324,71875296,"Bibice Mokra","3361"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245043,180479033,71881087,"Bibice Na Czekaj (nż)","3593"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244363,180463680,71939160,"Boleń Pętla","3210"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243251,180451434,71908936,"Węgrzce Błonie","1079"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242623,180431924,71908712,"Węgrzce Centrum","165"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243252,180463033,71910097,"Węgrzce Pętla","1080"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243305,180480128,72015844,"Książniczki Las (nż)","1143"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244364,180443880,71979120,"Bosutów Remiza","3211"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244303,180375012,71972064,"Dziekanowicka (nż)","3194"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244304,180382320,72005148,"Powstańców Magazyny","3195"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243676,180414360,71982720,"Bosutów Krakowska (nż)","2769"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244792,180396000,71967960,"Węgrzce B1 (nż)","3442"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244302,180388584,71985060,"Dziekanowice Batowice SKA","3193"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242753,180400541,72055448,"Batowice Kapliczka","352"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242769,180363308,72046616,"Os. Piastów","378"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243161,180378098,72043766,"Urząd Skarbowy Nowa Huta","957"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242771,180357523,72063616,"Popielidów","380"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243800,180394448,72030658,"Batowice Spławy (nż)","2959"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244361,180405000,72030312,"Dziekanowice Długa (nż)","3165"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243803,180410320,72081910,"Batowice Dłubnia","2962"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244362,180422172,72044064,"Dziekanowice Kapliczka","3166"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244650,180470824,72050262,"Książniczki Paszczykówka (nż)","3377"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243301,180448748,72080186,"Kończyce","1139"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242741,180513474,71892003,"Michałowice Komora (nż)","332"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243808,180540484,71845615,"Michałowice Brzozowa (nż)","2967"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243505,180528947,71865304,"Michałowice Kwiaty Polne (nż)","2506"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243238,180527335,71881130,"Michałowice Banasiówka (nż)","1058"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245044,180544583,71941537,"Michałowice Dąbrowskich (nż)","3594"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243307,180537945,71950545,"Michałowice Ogrodowa (nż)","1145"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243237,180569879,71881042,"Michałowice Ujęcie Wody (nż)","1055"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242743,180595109,71883618,"Michałowice Lecznica (nż)","335"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243654,180583869,71925504,"Michałowice Ośrodek Zdrowia","2729"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245039,180570382,71925165,"Michałowice P+R","3582"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245045,180570875,71929618,"Michałowice Centrum","3595"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245046,180599916,71921519,"Michałowice Górna (nż)","3596"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244614,180615427,71911446,"Michałowice Warszawka (nż)","3347"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243304,180486688,72033138,"Książniczki Centrum","1142"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243303,180519133,71976047,"Młodziejowice Park","1141"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243306,180502562,71978884,"Młodziejowice Młyn (nż)","1144"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244647,180577615,71963248,"Masłomiąca Koźlica (nż)","3374"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243822,180578755,71995448,"Masłomiąca Staw (nż)","2981"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244651,180568888,72047560,"Masłomiąca Długa (nż)","3378"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244798,180421021,72120711,"Raciborowice Wawelska","3452"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243688,180392005,72191293,"Zakole","2789"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242820,180382779,72157767,"Zesławice","447"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243592,180380620,72178774,"Zesławice Ogródki Działkowe (nż)","2621"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244506,180368327,72219694,"Niebyła (nż)","3278"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243300,180425052,72111451,"Raciborowice Centrum","1138"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243829,180426757,72145411,"Raciborowice Granica (nż)","2988"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244799,180436773,72181915,"Zastów Pętla","3453"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243398,180437683,72204329,"Zastów Działki (nż)","1265"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243399,180477038,72209521,"Wiktorowice Skrzyżowanie (nż)","1266"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243669,180357727,72230554,"Szpital Okulistyczny","2759"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242818,180363958,72249008,"Grębałów (nż)","443"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242819,180376675,72221721,"Kantorowice","445"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244130,180374788,72256370,"Morcinka (nż)","3119"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244565,180395907,72293379,"Prusy","3300"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242843,180409857,72306049,"Prusy Szkoła (nż)","478"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244300,180476280,72232920,"Wiktorowice Rozlewnia Wód (nż)","3189"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242841,180439082,72348526,"Sulechów (nż)","476"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242842,180428759,72331784,"Prusy WSR (nż)","477"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243825,180529920,72173880,"Pielgrzymowice Pętla","2984"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244652,180543240,72172800,"Pielgrzymowice Wierzbowa (nż)","3379"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243397,180508098,72204923,"Wiktorowice Granica (nż)","1264"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243400,180494999,72219466,"Wiktorowice Centrum (nż)","1267"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243828,180555494,72122761,"Więcławice Stare Wysyłek (nż)","2987"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244645,180556770,72104158,"Więcławice Stare Centrum (nż)","3372"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243827,180552600,72142200,"Pielgrzymowice Granica (nż)","2986"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243149,180573515,72128687,"Sieborowice (nż)","943"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244649,180603572,72126757,"Zagórzyce Stare Owocowa (nż)","3376"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244297,180493560,72251640,"Maciejowice Góra (nż)","1269"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244298,180511560,72260280,"Maciejowice Pętla","3159"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244299,180505080,72267840,"Maciejowice Skrzyżowanie (nż)","3168"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243671,180615337,72247918,"Łuczyce Zwierzyniec (nż)","2761"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243373,180593046,72271973,"Łuczyce Zjawienie (nż)","1231"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244551,180578160,72302400,"Łuczyce Kosynierów","3287"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243286,180573020,72332193,"Łuczyce Las (nż)","1120"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243444,180614401,72346244,"Marszowice Góry (nż)","1319"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244620,180794160,71925480,"Iwanowice Dworskie Pętla","3353"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245130,180768132,71839404,"Krasieniec Zakupny Kotryska (nż)","3641"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245131,180768132,71839404,"Iwanowice Dworskie Kotryska (nż)","3642"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244613,180621557,71860616,"Michałowice Laskowiec (nż)","3346"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244648,180629341,72142054,"Zagórzyce Dworskie Remiza","3375"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243673,180655920,72246600,"Goszcza Cmentarz (nż)","2763"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244553,180672480,72251640,"Goszcza Dworek","3289"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244554,180635800,72238806,"Sadowie Wiadukt (nż)","3290"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243842,180651150,72336303,"Marszowice Centrum (nż)","3003"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243843,180676647,72333259,"Marszowice (nż)","3004"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243446,180696480,72330011,"Polanowice Kolonia (nż)","1323"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244552,180713552,72329364,"Polanowice Boisko (nż)","3288"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243448,180763033,72309273,"Niedźwiedź PKP (nż)","1326"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243449,180805526,72295292,"Niedźwiedź Felix (nż)","1327"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243441,180866520,72290160,"Słomniki Rynek","1316"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243657,180875880,72298080,"Słomniki Kościuszki (nż)","2733"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243447,180831255,72295593,"Ratajów Skrzyżowanie (nż)","1325"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243442,180843840,72294840,"Słomniki Zagłoby (nż)","1317"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244555,180617425,72451081,"Goszyce Pod Lasem (nż)","3291"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242845,180386096,72399053,"Łuczanowice","480"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244270,180389520,72389880,"Orłowskiego (nż)","3187"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243595,180395287,72413186,"Bystronia (nż)","2627"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243385,180409680,72456120,"Krzysztoforzyce Góra (nż)","1248"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243389,180416520,72473760,"Krzysztoforzyce przy Dworze (nż)","1256"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242847,180380300,72439402,"Wadów Osiedle (nż)","483"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242848,180362099,72451901,"Wadów Szkoła","484"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242846,180401209,72432385,"Łuczanowice Skrzyżowanie","482"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242840,180457163,72367501,"Dojazdów PKS","475"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242839,180471240,72387720,"Dojazdów Działki (nż)","474"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243839,180480597,72411766,"Kocmyrzów Rondo","3000"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243390,180480960,72453600,"Kocmyrzów Krzyżówka (nż)","1257"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243840,180469175,72468508,"Kocmyrzów Biblioteka","3001"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243863,180414720,72487440,"Krzysztoforzyce Sklep (nż)","3043"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244391,180416880,72498240,"Krzysztoforzyce Pętla","3230"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244493,180357912,72533304,"Węgrzynowicka (nż)","3277"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243533,180372665,72537916,"Węgrzynowice Dół (nż)","2552"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243532,180386633,72545990,"Barwna (nż)","2551"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242851,180415267,72552359,"Węgrzynowice","487"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242852,180400280,72561226,"Węgrzynowice Centrum (nż)","488"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243844,180468482,72494844,"Kocmyrzów Sodfiny (nż)","3005"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243835,180475905,72520223,"Głęboka Kolonia (nż)","2996"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243866,180471204,72547524,"Głęboka Zakręt (nż)","3046"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244545,180479226,72537416,"Głęboka Staw","3281"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244556,180486278,72407554,"Kocmyrzów-Luborzyca Urząd Gminy","3292"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243841,180516143,72390827,"Luborzyca Szkoła","3002"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243836,180546522,72378479,"Wysiołek Luborzycki Skrzyżowanie (nż)","2997"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242838,180527134,72393358,"Luborzyca","472"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245063,180513965,72449309,"Kocmyrzów CPN (nż)","3587"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243838,180556640,72359881,"Wysiołek Luborzycki Kapkazy (nż)","2999"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243837,180572049,72373789,"Wysiołek Luborzycki (nż)","2998"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243454,180557122,72416865,"Wola Luborzycka Działki (nż)","1332"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243445,180589275,72360234,"Wysiołek Luborzycki Radwany (nż)","1320"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244548,180577349,72421438,"Wola Luborzycka Skrzyżowanie (nż)","3284"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243154,180592457,72428860,"Wola Luborzycka Las (nż)","950"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245059,180601200,72562320,"Rawałowice Kapliczka","3583"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245062,180541440,72494352,"Pietrzejowice (nż)","3586"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245061,180557424,72522252,"Rawałowice Góra (nż)","3585"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875245060,180590400,72533880,"Rawałowice Skrzyżowanie (nż)","3584"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875242856,180409070,72682572,"Wróżenice","499"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244544,180441000,72674280,"Czulice Przymiarki (nż)","3280"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244546,180473400,72615960,"Karniów Szkoła","3282"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244547,180457200,72676800,"Czulice Kościół","3283"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243611,180378016,72616515,"Ostafina","2643"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243608,180404271,72641574,"Wróżenicka (nż)","2640"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243610,180394837,72631857,"Hektary (nż)","2642"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243609,180406133,72657050,"Wróżenice Górka (nż)","2641"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243158,180641784,72474109,"Goszyce Remiza (nż)","954"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875243156,180686160,72563760,"Łososkowice Szkoła (nż)","952"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244549,180675720,72576720,"Łososkowice Remiza","3285"))
        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278766,180367133,72043450,"Os. Piastów","378"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278732,180234831,71825984,"Lubicz","126"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279100,180064597,71601349,"Czerwone Maki P+R","3038"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278875,180074257,71632051,"Chmieleniec","2691"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278874,180087693,71646235,"Kampus UJ","2690"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278838,180047695,71736393,"Borek Fałęcki","747"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278840,180052313,71740458,"Borek Fałęcki I","824"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278837,180058468,71746670,"Solvay","746"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278823,180080431,71757823,"Sanktuarium Bożego Miłosierdzia","615"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278836,180048914,71821671,"Kurdwanów","744"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279369,180048914,71821671,"Kurdwanów P+R","3176"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278889,180088119,71762192,"Łagiewniki ZUS","2821"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278723,180263081,71696364,"Biprostal","84"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278741,180189697,71695213,"Salwator","311"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278817,180097653,71660276,"Ruczaj","589"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278873,180108113,71685718,"Norymberska","2688"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278745,180220900,71679150,"Reymana","320"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278747,180212247,71759953,"Filharmonia","322"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278807,180157950,71809135,"Korona","571"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278872,180114083,71714885,"Grota-Roweckiego","2687"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278871,180110753,71729800,"Lipińskiego","2686"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278820,180109861,71743736,"Borsucza","612"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278813,180124296,71733518,"Kobierzyńska","584"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278821,180109799,71754497,"Brożka (nż)","613"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278812,180148135,71727501,"Słomiana","577"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278819,180119708,71778550,"Rzemieślnicza","611"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278822,180114207,71762733,"PT","614"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278842,180107850,71769998,"Łagiewniki","922"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278818,180131163,71786300,"Rondo Matecznego","610"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278808,180149478,71795415,"Smolki","572"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278743,180213932,71730079,"Cracovia","318"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279310,180213932,71730079,"Muzeum Narodowe","3141"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278811,180162052,71719420,"Kapelanka","576"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278809,180174943,71751643,"Most Grunwaldzki","574"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279101,180174899,71751717,"Centrum Kongresowe ICE","3039"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279485,180174899,71751717,"Rondo Grunwaldzkie","3338"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278810,180168366,71731454,"Szwedzka","575"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278742,180192257,71718218,"Komorowskiego","313"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278846,180217018,71704348,"Park Jordana","960"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278839,180215404,71714643,"Oleandry","823"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278744,180198915,71739237,"Jubilat","319"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278746,180217279,71752656,"Uniwersytet Jagielloński","321"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278754,180182070,71778588,"Orzeszkowej","361"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278752,180186102,71790433,"Stradom","359"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278878,180179604,71810604,"Muzeum Inżynierii Miejskiej","2726"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278753,180172775,71796294,"Plac Wolnica","360"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278877,180182304,71822304,"Dajwór","915"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278880,180183978,71823985,"Św.Wawrzyńca","2742"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279631,180183978,71823985,"św. Wawrzyńca","3471"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278749,180196182,71782457,"Wawel","325"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278853,180213089,71776500,"Plac Wszystkich Świętych","1360"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278750,180214076,71792015,"Poczta Główna","357"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278879,180210340,71786990,"Św.Gertrudy","2741"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279630,180210340,71786990,"św. Gertrudy","3470"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278751,180206010,71803581,"Starowiślna","358"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278755,180192826,71814352,"Miodowa","362"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278756,180211207,71816125,"Hala Targowa","363"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278738,180284716,71630238,"Wesele","133"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278724,180226642,71651287,"Cichy Kącik","87"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278847,180274061,71659664,"Głowackiego","1049"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278726,180279461,71644220,"Bronowice","89"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278725,180267420,71677535,"Uniwersytet Pedagogiczny","88"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278740,180295027,71577170,"Bronowice Małe","135"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278739,180293148,71601153,"Balicka Wiadukt","134"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279226,180293078,71601237,"Bronowice Wiadukt","136"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279586,180293140,71600923,"Bronowice SKA","3459"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278711,180303281,71761873,"Bratysławska","61"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278720,180240862,71745438,"Batorego","78"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278719,180228878,71758536,"Teatr Bagatela","77"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278721,180249160,71735097,"Plac Inwalidów","79"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278722,180259199,71711001,"Urzędnicza","83"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278717,180255545,71801840,"Politechnika","73"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279096,180239091,71780963,"Stary Kleparz","3032"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278718,180239020,71781145,"Basztowa LOT","75"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278716,180250622,71775251,"Pędzichów","72"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278737,180232794,71802022,"Dworzec Główny","131"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279380,180232736,71802072,"Teatr Słowackiego","3242"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278863,180244921,71803299,"Dworzec Główny Zachód","2608"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278850,180246297,71809578,"Dworzec Główny Tunel","1173"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278714,180272154,71784192,"Dworzec Towarowy","70"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278715,180264512,71771333,"Nowy Kleparz","71"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278712,180319272,71753242,"Krowodrza Górka","63"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278713,180290620,71776085,"Prądnicka","69"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279098,180290484,71776260,"Szpital Narutowicza","3036"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278835,180044563,71842829,"Witosa","718"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278833,180048499,71873272,"Nowosądecka","715"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278826,180084121,71929954,"Bieżanowska","630"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278834,180055534,71897389,"Piaski Nowe","716"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278827,180066423,71919126,"Dauna","632"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278828,180070051,71956735,"Wlotowa","634"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278832,180052049,71998775,"Prokocim Szpital","682"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278829,180063373,71967644,"Prokocim","637"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278831,180059594,72027118,"Teligi","681"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278862,180058408,72045634,"Nowy Prokocim","2582"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278830,180057659,72070712,"Ćwiklińskiej","679"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278861,180056187,72082990,"Nowy Bieżanów","2580"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279368,180056187,72082990,"Nowy Bieżanów P+R","3175"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278760,180217301,71894481,"Fabryczna","368"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278865,180151766,71859715,"Powstańców Wielkopolskich","568"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279348,180290620,71776085,"Podgórze SKA","3158"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278867,180139866,71872993,"Cmentarz Podgórski","621"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278825,180096973,71916944,"Kabel","624"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278824,180119078,71894471,"Dworcowa","623"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278952,180120525,71917500,"Dworzec Płaszów Estakada","2870"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278803,180146336,71920669,"Gromadzka","560"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278804,180146469,71939257,"Lipska","561"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278866,180162383,71839584,"Limanowskiego","569"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278806,180167637,71838366,"Plac Bohaterów Getta","570"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278849,180179166,71873518,"Zabłocie","1154"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278844,180176268,71883649,"Klimeckiego","946"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278758,180207818,71852584,"Rondo Grzegórzeckie","365"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278854,180211890,71864976,"Kordylewskiego","2536"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278914,180211819,71864863,"Teatr Variété","2859"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278759,180216338,71881211,"Francesco Nullo","367"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278762,180219707,71925633,"Dąbie","370"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278805,180160654,71904812,"Kuklińskiego","567"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278761,180214611,71914779,"Ofiar Dąbia","369"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278852,180143662,72005402,"Mały Płaszów","1263"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279480,180143662,72005402,"Mały Płaszów P+R","3310"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278851,180145794,71970297,"Rzebika","1262"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278735,180239469,71891259,"Cystersów","129"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278731,180236041,71853530,"Rondo Mogilskie","125"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278734,180254686,71835785,"Rakowicka (nż)","128"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279762,180254686,71835785,"Muzeum Fotografii (nż)","3621"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278733,180244685,71828772,"Uniwersytet Ekonomiczny","127"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278730,180264956,71844258,"Cmentarz Rakowicki","124"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278729,180257919,71942105,"Wieczysta","114"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279102,180257803,71942128,"TAURON Arena Kraków Wieczysta","3040"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278736,180243813,71919353,"Białucha","130"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278845,180226861,71942233,"Kraków Plaza","959"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279097,180226726,71941976,"Plaza (nż)","3033"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278855,180230163,71954541,"Lema","2537"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278885,180231471,71962356,"Kraków Arena Al. Pokoju","2803"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278953,180231482,71962198,"TAURON Arena Kraków Al. Pokoju (nż)","2871"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279633,180231482,71962198,"TAURON Arena Kraków al. Pokoju","3473"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278843,180236053,71988794,"M1 Al. Pokoju","930"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279632,180236053,71988794,"M1 al. Pokoju","3472"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278763,180242565,72019445,"Nowohucka","372"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279103,180242602,72019457,"Rondo 308. Dywizjonu","3041"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278888,180264612,71965384,"Muzeum Lotnictwa","2811"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278728,180266265,71996755,"AWF","113"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278727,180267446,72016931,"Stella-Sawickiego","112"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278775,180249472,72044791,"Centralna","409"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278773,180266390,72041060,"Czyżyny","407"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278774,180265067,72061596,"Rondo Czyżyńskie","408"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278841,180273208,72075195,"Bieńczycka","867"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278764,180342391,71986739,"Mistrzejowice","375"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278765,180337361,72015690,"Os. Złotego Wieku","377"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278856,180340084,71999812,"Miśnieńska","2538"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278770,180332424,72056920,"Dunikowskiego","388"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278771,180304250,72074134,"DH Wanda","392"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278769,180339761,72040494,"Rondo Piastowskie","383"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278768,180344562,72045557,"Kleeberga","382"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278767,180353434,72046888,"Piasta Kołodzieja","379"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278857,180324306,72077018,"Rondo Hipokratesa","2539"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278881,180259494,72135185,"Plac Centralny im. R.Reagana","2744"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278776,180260346,72104898,"Os. Kolorowe","413"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278882,180286510,72098620,"Rondo Kocmyrzowskie im. Ks. Gorzelanego","2745"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278778,180276386,72112242,"Os. Zgody","418"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278783,180246786,72189420,"Klasztorna","429"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278782,180251143,72164924,"Os. Na Skarpie","424"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278859,180242960,72212828,"Suche Stawy","2548"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278781,180268841,72168894,"Struga","423"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278780,180295778,72116868,"Teatr Ludowy","420"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279099,180306450,72136926,"Cienista","3037"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278772,180306733,72137208,"Kocmyrzowska","401"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278858,180313263,72171640,"Wańkowicza","2543"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278784,180318123,72204362,"Wiadukty","434"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278860,180330387,72217988,"Cmentarz Grębałów Zachód","2549"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278870,180330358,72217850,"Jarzębiny","2685"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278785,180326907,72212839,"Darwina","435"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278798,180315951,72286426,"Agencja Kraków Wschód","462"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278799,180322790,72300966,"Walcownia","463"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278787,180244244,72236407,"Bardosa","449"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278788,180249368,72243352,"Kopiec Wandy","450"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278789,180233825,72269721,"Brama nr 4 (nż)","451"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278848,180238786,72256369,"Fort Mogiła (nż)","1051"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278795,180285303,72236590,"Kombinat","459"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278790,180232684,72304711,"Jeżynowa","452"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856279604,180232830,72304668,"Giedroycia (nż)","3174"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278791,180250342,72332498,"Brama nr 5 (nż)","453"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278792,180251551,72342602,"Meksyk (nż)","454"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278786,180341535,72234935,"Wzgórza Krzesławickie","442"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278802,180303027,72232399,"PH","466"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278801,180300788,72236130,"Zajezdnia Nowa Huta","465"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278800,180315729,72231482,"Elektromontaż (nż)","464"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278796,180302996,72258514,"Mrozowa","460"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278797,180307241,72267887,"Blokowa","461"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278794,180263218,72423770,"Pleszów","458"))

        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.TRAM,8059230041856278793,180258540,72375333,"Koksochemia (nż)","457"))


        listOfBusStops.add(Insert_db_BusStopInterface.BusStopRow(Insert_db_VehicleTypeInterface.Vehicle.BUS,8095258838875244550,180679445,72521518,"Skrzeszowice OSP Boisko","3286"))



        for (busStop in listOfBusStops){
            insertBusStopDatabase.insertRow(instance.writableDatabase,busStop)
        }

    }
}