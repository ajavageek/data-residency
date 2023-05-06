package ch.frankel.blog.dataresidency

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm


class LocationBasedSharding : StandardShardingAlgorithm<String> {

    override fun doSharding(targetNames: MutableCollection<String>, shardingValue: PreciseShardingValue<String>) =
        when (shardingValue.value) {
            "fr" -> "europe"
            "us" -> "usa"
            else -> throw IllegalArgumentException("No sharding over ${shardingValue.value} defined")
        }

    override fun doSharding(targetNames: MutableCollection<String>, shardingValue: RangeShardingValue<String>): MutableCollection<String> {
        TODO("Not yet implemented")
    }
}
