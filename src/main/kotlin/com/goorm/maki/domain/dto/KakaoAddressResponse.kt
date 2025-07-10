package com.goorm.maki.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoAddressResponse(
    val meta: KakaoAddressMeta,
    val documents: List<KakaoAddressDocument>
)

data class KakaoAddressMeta(
    @JsonProperty("total_count") val totalCount: Int,
    @JsonProperty("pageable_count") val pageableCount: Int,
    @JsonProperty("is_end") val isEnd: Boolean
)

data class KakaoAddressDocument(
    @JsonProperty("address_name") val addressName: String,
    val x: String,
    val y: String,
    @JsonProperty("address_type") val addressType: String,
    val address: KakaoAddress?,
    @JsonProperty("road_address") val roadAddress: KakaoRoadAddress?
)

data class KakaoAddress(
    @JsonProperty("address_name") val addressName: String,
    @JsonProperty("region_1depth_name") val region1DepthName: String,
    @JsonProperty("region_2depth_name") val region2DepthName: String,
    @JsonProperty("region_3depth_name") val region3DepthName: String,
    @JsonProperty("region_3depth_h_name") val region3DepthHName: String,
    @JsonProperty("h_code") val hCode: String,
    @JsonProperty("b_code") val bCode: String,
    @JsonProperty("mountain_yn") val mountainYn: String,
    @JsonProperty("main_address_no") val mainAddressNo: String,
    @JsonProperty("sub_address_no") val subAddressNo: String,
    val x: String,
    val y: String
)

data class KakaoRoadAddress(
    @JsonProperty("address_name") val addressName: String,
    @JsonProperty("region_1depth_name") val region1DepthName: String,
    @JsonProperty("region_2depth_name") val region2DepthName: String,
    @JsonProperty("region_3depth_name") val region3DepthName: String,
    @JsonProperty("road_name") val roadName: String,
    @JsonProperty("underground_yn") val undergroundYn: String,
    @JsonProperty("main_building_no") val mainBuildingNo: String,
    @JsonProperty("sub_building_no") val subBuildingNo: String,
    @JsonProperty("building_name") val buildingName: String,
    @JsonProperty("zone_no") val zoneNo: String,
    val x: String,
    val y: String
)