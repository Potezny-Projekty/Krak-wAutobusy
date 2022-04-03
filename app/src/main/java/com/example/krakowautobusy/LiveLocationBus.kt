package com.example.krakowautobusy



class AllLiveBus(public val vehicles:List<LiveBus>)
class LiveBus(val id :String,val latitude:Long=-1,val longitude:Long=-1)