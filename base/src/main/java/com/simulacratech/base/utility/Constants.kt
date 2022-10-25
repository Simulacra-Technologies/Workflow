package com.simulacratech.base.utility

const val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
const val FILTER_REQUEST = 100
const val EDIT_REQUEST = 101
const val PLACE_SEARCH = 102
const val DETAILS_REQUEST = 103
const val SEARCH_SELECT_REQUEST = 104
const val MAP_LAYER_REQUEST = 105
const val AR_MEASURE_REQUEST = 106
const val UPDATE_PROGRESS_MAP_REQUEST = 107
const val UPDATE_INTERVAL: Long = 30000
const val FASTEST_INTERVAL: Long = 20000
const val MAX_PRICE_VALUE = 100000
const val MAX_LOCATION_TRY = 3
const val PARAM_LATITUDE: String = "MAP_LATITUDE"
const val PARAM_LONGITUDE: String = "MAP_LONGITUDE"
const val PARAM_LOCATION = "location"
const val DATA_KEY = "data"
const val PARAM_ID = "id"
const val PARAM_HOTSPOT_ID = "hotspotId"
const val PARAM_FEEDER_DATA = "feederData"
const val PARAM_HOTSPOT_DATA = "hotspotData"
const val PARAM_HOTSPOT_TITLE = "hotspotTitle"
const val PARAM_RISK_LEVEL = "riskLevel"
const val PARAM_TASK = "taskId"
const val PARAM_HELP = "helpId"
const val PARAM_STATUS_ID = "statusId"
const val PARAM_STATUS_TEXT = "status"
const val PARAM_BOOLEAN = "booleanData"
const val PARAM_TYPE = "type"
const val PARAM_TAB_TYPE = "tabType"
const val PARAM_IS_UPDATE = "isUpdate"
const val PARAM_FILTER = "filter"
const val PARAM_SORT = "sort"
const val PARAM_POSITION = "position"
const val PARAM_IS_FROM_MAP = "from_map"
const val PARAM_IS_FROM_LIST = "fromList"
const val PARAM_TITLE = "title"
const val PARAM_SHOULD_SHOW_SKIP = "shouldShowSkip"
const val PARAM_INITIAL_SELECTED_TAB_POSITION = "initialSelectedTabPosition"
const val PARAM_IS_MITIGATION = "isMitigation"
const val PARAM_IS_MITIGATION_WITH_ASSESSMENT = "isMitigationWithAssessment"
const val PARAM_IS_ADD_MORE = "isAddMore"
const val PARAM_IS_SINGLE_SELECT = "isSingleSelect"
const val PARAM_WORK_IDENTIFIED_ID = "workIdentifiedId"
const val PARAM_INITIAL_SELECTED_ITEM = "initialSelectedItem"
const val PARAM_RISK_TYPE = "riskType"
const val PARAM_IS_TRIMMING = "isTrimming"
const val PARAM_IS_WORK_TYPE_MAP_VIEW = "isWorkTypeMapViw"

const val PARAM_DEVICE_TYPE = "DVC_TYPE"
const val PARAM_VOLTAGE = "voltage"
const val PARAM_PHASE = "phase"
const val PARAM_CUSTOMER = "customer"
const val PARAM_STATUS_CAPITAL_LETTERS = "STATUS"
const val PARAM_ID_CAPITAL_LETTERS = "ID"
const val PARAM_SOURCE = "source"
const val PARAM_IS_FOR_SVEC_CLIENT = "isForSvecClient"
const val PARAM_INFORMATION = "information"
const val PARAM_RISK_DATA = "riskData"
const val PARAM_RISK_FILTER = "riskFilter"
const val PARAM_RISK_SEGMENT_DATA = "riskSegmentData"
const val PARAM_RISK_FIRST_LEVEL_DETAIL = "riskFirstLevelDetail"
const val PARAM_IS_FOR_DUKE_CLIENT = "isForDukeClient"
const val PARAM_FEEDER = "feeder"
const val PARAM_FEEDER_SEGMENT = "feeder_segment"
const val PARAM_WORK_TYPE = "work_type"
const val PARAM_VEG_TYPE = "veg_type"
const val PARAM_CHIP_WORK = "chip_work"
const val PARAM_SPAN_ID = "spanId"
const val PARAM_ACTION_TYPES = "actionTypes"

const val PARAM_MAX = "maxValue"
const val PARAM_MIN = "minValue"

// TASK UPDATE DATA PARAMS
const val PARAM_COST = "cost"
const val PARAM_HOURS = "hours"
const val PARAM_LINE_MILES = "lineMiles"

// EVENT PARAMS
const val PARAM_USER = "year"
const val PARAM_TIME = "success"
const val PARAM_ONLINE = "isOnline"

const val IMAGE_CAPTURE = 100
const val GALLERY = 101

const val NEVER_ASK = 1
const val GRANTED = 2
const val DENIED = 3
const val SHOW_RATIONALE = 4

/*const val maxNumMultiplePoints = 6
const val multipleDistanceTableHeight = 300

const val arrowViewSize = 45*/

const val IS_HAZARD_ALLOW = true
const val IS_CYCLE_TRIM_ALLOW = true
const val IS_SPAN_TRIM_ALLOW = false
const val IS_ALL_FEATURE_ALLOW = false

const val MASTER_DATA_VERSION = 1
const val cycleTaskModuleValueInDb = "cycle_task"
const val riskTaskModuleValueInDb = "risk_task"
val newStatusForCycleCardLayout = listOf("Accepted", "In-Progress")
val newStatusForRiskCardLayout = listOf(
    "Unassigned", "New", "Assigned"
)/*temporary for showcasing different type of cards*/
const val acceptedStatus = "Accepted"
const val assignedStatus = "Assigned"
val completedStatusOrHigher = listOf(
    "Completed", "Audited"
)
val addWorkStatusForRiskDetails = listOf(
    acceptedStatus, "In-progress"
)/*temporary for showcasing different type of cards*/
val showContractorListForTransitionTitle = listOf(
    "Assign", "Contractor"
)/*temporary list to determine if contractor list should be shown */
val disableStatusChangeButtonMap = listOf("Complete")
internal const val sliderFilterType = "SLIDER"
internal const val checkboxFilterType = "CHECKBOX"
internal const val indexOfFirstSection = 0
internal const val criticalityScoreIdentifierForRiskFilter = "criticality"
internal const val riskLevelIdentifierForRiskFilter = "risklevel"
internal const val hazardTreeRiskIdentifierForRiskFilter = "HazardTreeRisk"
internal const val riskScoreLevelApiRequestKeyForRiskFilter = "riskScoreLevels"
internal const val hazardTreeRiskApiRequestKeyForRiskFilter = "hazardTreeRiskLevels"
/* following temp values are working for Avista test environment only
const val workIdentifiedForTreeRemoval = "4cd3e9aa-6673-424a-90db-ad2b5f34e35d"
const val workIdentifiedForTrimming = "8fb31cb3-e264-4ae3-8ca9-4b9dbe86e59a"*/

const val FREEHAND_COLOR: String = "#a0861c"
const val FREEHAND_LINE_WIDTH: Float = 2.5f
const val FREEHAND_LINE_OPACITY: Float = 1f

const val PROGRESS_COLOR: String = "#0747A6"   //"#192FE5"
const val PROGRESS_LINE_WIDTH: Float = 8f
const val PROGRESS_LINE_OPACITY: Float = 0.45f

const val COMPLETED_COLOR: String = "#3CCCB4"    //"#10C3CB"
const val COMPLETED_LINE_WIDTH: Float = 8f
const val COMPLETED_LINE_OPACITY: Float = 0.6f

const val SPAN_CONDUCTOR_COLOR: String = "#EB8B3E"     //"#3A3845"
const val SPAN_CONDUCTOR_ADD_WORK_COLOR: String = "#EB8B3E"//old (light pink) #FFC0E7
const val SPAN_CONDUCTOR_LINE_WIDTH: Float = 2.2f
const val SPAN_CONDUCTOR_LINE_OPACITY: Float = 1f

const val COMPLETED_COLOR_FOR_MITIGATION: String = "#0C9F09"

const val ICON_PROPERTY: String = "Icon-property"
const val CYCLE_TRIM_MARKER_ID = "CYCLE_TRIM_MARKER_ID"
const val CIRCUIT_MARKER_SOURCE_ID = "CIRCUIT_MARKER_SOURCE_ID"
const val CIRCUIT_MARKER_LAYER_ID = "CIRCUIT_MARKER_LAYER_ID"
const val WORK_LOGGED_MARKER_ID = "WORK_LOGGED_MARKER_ID"
const val WORK_LOGGED_MARKER_SOURCE_ID = "WORK_LOGGED_MARKER_SOURCE_ID"
const val WORK_LOGGED_MARKER_LAYER_ID = "WORK_LOGGED_MARKER_LAYER_ID"
const val FREEHAND_DRAW_LINE_LAYER_SOURCE_ID = "FREEHAND_DRAW_LINE_LAYER_SOURCE_ID"
const val FREEHAND_DRAW_LINE_LAYER_ID = "FREEHAND_DRAW_LINE_LAYER_ID"
const val SPAN_CONDUCTOR_SOURCE_ID = "SPAN_CONDUCTOR_SOURCE_ID"
const val SPAN_CONDUCTOR_LAYER_ID = "SPAN_CONDUCTOR_LAYER_ID"
const val SPAN_COMPLETED_CONDUCTOR_SOURCE_ID = "SPAN_COMPLETED_CONDUCTOR_SOURCE_ID"
const val SPAN_COMPLETED_CONDUCTOR_LAYER_ID = "SPAN_COMPLETED_CONDUCTOR_LAYER_ID"
const val SPAN_COMPLETED_MITIGATION_CONDUCTOR_SOURCE_ID =
    "SPAN_COMPLETED_MITIGATION_CONDUCTOR_SOURCE_ID"
const val SPAN_COMPLETED_MITIGATION_CONDUCTOR_LAYER_ID =
    "SPAN_COMPLETED_CONDUCTOR_MITIGATION_LAYER_ID"
const val SPAN_PROGRESS_CONDUCTOR_SOURCE_ID = "SPAN_PROGRESS_CONDUCTOR_SOURCE_ID"
const val SPAN_PROGRESS_CONDUCTOR_LAYER_ID = "SPAN_PROGRESS_CONDUCTOR_LAYER_ID"


const val BASE_URL_TENANT_CONFIG = "https://ivms-mobile.aidash.org/api/"

class Constants {

    companion object {

        const val completeTreeRemoval: String = "completeTreeRemoval"
        const val mitigationTreeRemovalLogWork: String = "mitigationTreeRemovalLogWork"
        const val trimmingAssessment: String = "trimmingAssessment"
        const val PAGINATION_MAX: Int = 10

        const val MIN_OPENGL_VERSION: Double = 3.0

        const val FOR_SVEC_CLIENT: Boolean = true
        const val FOR_DUKE_CLIENT: Boolean = false

        // to show walk through of the app
        const val PREF_FIRST_TIME = "firstTime"

        const val PREF_IS_LOGGEDIN = "IS_LOGIN"
        const val PREF_MASTER_DATA_DOWNLOADED = "IS_MST_HAZARD_DATA_INSERTED"

        //to keep track of the time in millis the master data was last downloaded
        const val PREF_MASTER_DATA_DOWNLOAD_TIME = "MASTER_DOWNLOAD_TIME"
        const val PREF_MASTER_DATA_VERSION = "master_data_version"

        //        const val PREF_LOGIN_USER_ROLE = "LOGIN_USER_ROLE"
        const val PREF_IVMS_ACCESS_TOKEN = "IVMS_TOKEN"
        const val PREF_IVMS_TOKEN_EXPIRY = "tokenExpiry"

        const val PREF_IVMS_APP_TITLE = "app_title"
        const val PREF_IVMS_TENANT_CODE = "tenantCode"
        const val PREF_IVMS_AUTH0_DOMAIN = "auth0Domain"
        const val PREF_IVMS_AUTH0_CLIENTID = "auth0ClientId"
        const val PREF_IVMS_AUTH0_AUTH_SCHEME = "authScheme"
        const val PREF_IVMS_AUTH0_AUDIENCE = "auth0Audience"
        const val PREF_IVMS_API_ENDPOINT_URL = "apiEndpointUrl"
        const val PREF_IVMS_AUTH_ENDPOINT_URL = "authEndpointUrl"
        const val PREF_IVMS_BACKFIL_ENDOINT_URL = "backfilEndpointUrl"
        const val PREF_IVMS_IMAGE_ACCESS_ENDPOINT_URL = "imageAccessRootUrl"
        const val PREF_IVMS_HELP_URL = "helpUrl"
        const val PREF_IVMS_HELP_AUTH_TOKEN = "helpAuthToken"
        const val PREF_IVMS_IS_WORKORDER_MODULE_ENABLED = "KEY_IS_WORKORDER_MODULE_ENABLED"
        const val PREF_IVMS_IS_HT_MODULE_ENABLED = "KEY_IS_HT_MODULE_ENABLED"
        const val PREF_IVMS_IS_CT_MODULE_ENABLED = "KEY_IS_CT_MODULE_ENABLED"
        const val PREF_IVMS_IS_ACTIVE_STATUS_SPLIT_ENABLED_IN_CT =
            "KEY_IS_ACTIVE_STATUS_SPLIT_ENABLED_IN_CT"
        const val PREF_IVMS_IS_AHP_MODULE_ENABLED = "KEY_IS_AHP_MODULE_ENABLED"
        const val PREF_IVMS_IS_RM_MODULE_ENABLED = "KEY_IS_RM_MODULE_ENABLED"
        const val PREF_IVMS_RM_ENTITIES = "KEY_RM_ENTITIES"
        const val PREF_IVMS_CT_ENTITIES = "KEY_CT_ENTITIES"

        const val API_SUFFIX = "/api/"
        const val ENTITIES_PREF_SEPARATOR = ","

        const val OC = "OC"
        const val Contractor = "Contractor"
        const val Lineman = "Lineman"
        const val SystemAdmin = "System Admin"
        const val Supervisor = "Supervisor"

        const val ZOOM_LEVEL_POLES = 13.0
        const val ZOOM_LEVEL_POLES_FHP = 16.0
        const val ZOOM_LEVEL_DEVICES = 7.0
        const val ZOOM_LEVEL_SPAN = 10.0

        const val PREF_CURRENT_SESSION_TIMING = "CURRENT_SESSION_TIMING"
        const val PREF_IS_SESSION_ONLINE = "IS_SESSION_ONLINE"

        const val PREF_IS_FIRST_TIME_UNAPPROVED = "IS_FIRST_TIME_UNAPPROVED"
        const val PREF_IS_DATA_FDB_EXHAUSTED_UNAPPROVED = "IS_DATA_FDB_EXHAUSTED_UNAPPROVED"
        const val PREF_IS_DATA_FSERVER_EXHAUSTED_UNAPPROVED = "IS_DATA_FSERVER_EXHAUSTED_UNAPPROVED"
        const val PREF_FETCH_FDB_ROWOFFSET_UNAPPROVED = "FETCH_FDB_ROWOFFSET_UNAPPROVED"
        const val PREF_FETCH_FSERVER_ROWOFFSET_UNAPPROVED = "FETCH_FSERVER_ROWOFFSET_UNAPPROVED"

        const val PREF_IS_FIRST_TIME_UNASSIGNED = "IS_FIRST_TIME_UNASSIGNED"
        const val PREF_IS_DATA_FDB_EXHAUSTED_UNASSIGNED = "IS_DATA_FDB_EXHAUSTED_UNASSIGNED"
        const val PREF_IS_DATA_FSERVER_EXHAUSTED_UNASSIGNED = "IS_DATA_FSERVER_EXHAUSTED_UNASSIGNED"
        const val PREF_FETCH_FDB_ROWOFFSET_UNASSIGNED = "FETCH_FDB_ROWOFFSET_UNASSIGNED"
        const val PREF_FETCH_FSERVER_ROWOFFSET_UNASSIGNED = "FETCH_FSERVER_ROWOFFSET_UNASSIGNED"

        const val PREF_IS_FIRST_TIME_COMPLETED = "IS_FIRST_TIME_COMPLETED"
        const val PREF_IS_DATA_DB_EXHAUSTED_COMPLETED = "IS_DATA_FDB_EXHAUSTED_COMPLETED"
        const val PREF_IS_DATA_SERVER_EXHAUSTED_COMPLETED = "IS_DATA_FSERVER_EXHAUSTED_COMPLETED"
        const val PREF_FETCH_DB_ROWOFFSET_COMPLETED = "FETCH_FDB_ROWOFFSET_COMPLETED"
        const val PREF_FETCH_SERVER_ROWOFFSET_COMPLETED = "FETCH_FSERVER_ROWOFFSET_COMPLETED"

        const val PREF_FETCH_SERVER_ACTIVE = "FETCH_FSERVER_ACTIVE"
        const val PREF_IS_FIRST_TIME_ACTIVE = "IS_FIRST_TIME_WOKRING"
        const val PREF_IS_DATA_EDITED_ACTIVE = "IS_DATA_EDITED_ACTIVE"
        const val PREF_IS_DATA_DB_EXHAUSTED_ACTIVE = "IS_DATA_FDB_EXHAUSTED_ACTIVE"
        const val PREF_IS_DATA_SERVER_EXHAUSTED_ACTIVE = "IS_DATA_FSERVER_EXHAUSTED_ACTIVE"
        const val PREF_FETCH_DB_ROWOFFSET_ACTIVE = "FETCH_FDB_ROWOFFSET_ACTIVE"
        const val PREF_FETCH_SERVER_ROWOFFSET_ACTIVE = "FETCH_FSERVER_ROWOFFSET_ACTIVE"

        const val PREF_IS_FIRST_TIME_DELETED = "IS_FIRST_TIME_DELETED"
        const val PREF_IS_DATA_FDB_EXHAUSTED_DELETED = "IS_DATA_FDB_EXHAUSTED_DELETED"
        const val PREF_IS_DATA_FSERVER_EXHAUSTED_DELETED = "IS_DATA_FSERVER_EXHAUSTED_DELETED"
        const val PREF_FETCH_FDB_ROWOFFSET_DELETED = "FETCH_FDB_ROWOFFSET_DELETED"
        const val PREF_FETCH_FSERVER_ROWOFFSET_DELETED = "FETCH_FSERVER_ROWOFFSET_DELETED"

        const val PREF_IS_FIRST_TIME_ASSIGNED = "IS_FIRST_TIME_ASSIGNED"
        const val PREF_IS_DATA_FDB_EXHAUSTED_ASSIGNED = "IS_DATA_FDB_EXHAUSTED_ASSIGNED"
        const val PREF_IS_DATA_FSERVER_EXHAUSTED_ASSIGNED = "IS_DATA_FSERVER_EXHAUSTED_ASSIGNED"
        const val PREF_FETCH_FDB_ROWOFFSET_ASSIGNED = "FETCH_FDB_ROWOFFSET_ASSIGNED"
        const val PREF_FETCH_FSERVER_ROWOFFSET_ASSIGNED = "FETCH_FSERVER_ROWOFFSET_ASSIGNED"

        const val PREF_MAP_IS_FETCHED_NEEDED = "MAP_IS_FETCHED_NEEDED"
        const val PREF_LAST_LOCATION_LAT = "LAST_LOCATION_LAT"
        const val PREF_LAST_LOCATION_LONG = "LAST_LOCATION_LONG"
        const val PREF_LAST_LOCATION_TIMING = "LAST_LOCATION_TIMING"

        const val PREF_APP_VERSION = "appVersion"
        const val PREF_IS_DEVICE_SUPPORT_AR = "DEVICE_AR_SUPPORT"

        // check if should show user compulsory update
        const val PREF_IS_HARD_RESET = "hardReset"

        //check if update is available
        const val PREF_UPDATE_AVAILABLE = "isUpdateAvailable"

        // date update dialog shown last is saved here
        const val PREF_UPDATE_DIALOG_DATE = "updateDialogShownDate"

        /*const val PREF_CLIENT_HAZARD_ACCESS_ALLOWED = "ALLOWED_HAZARD_ACCESSED"
        const val PREF_CLIENT_CTRIM_ACCESS_ALLOWED = "ALLOWED_CTRIM_ACCESSED"
        const val PREF_CLIENT_HSPOT_ACCESS_ALLOWED = "ALLOWED_HSPOT_ACCESSED"
        const val PREF_CLIENT_ACCESS_TO_ALL = "ALLOW_ACCESS_TO_ALL"*/
        const val PREF_PERMISSION_SET = "user_permission"

        const val DEVICE_VECTOR_MA_TILE_ID = "mapbox://aidash-ivms.0yb7158x"
        const val DEVICE_VECTOR_MA_LAYER_ID = "ng-devices-3cnnmr"
        const val DEVICE_VECTOR_NY_TILE_ID = "mapbox://aidash-ivms.ny-devices-all"
        const val DEVICE_VECTOR_NY_LAYER_ID = "ny_test"

        const val POLE_VECTOR_MA_TILE_ID = "mapbox://aidash-ivms.ma-poles-all"
        const val POLE_VECTOR_MA_LAYER_ID = "ma_poles"
        const val POLE_VECTOR_NY_TILE_ID = "mapbox://aidash-ivms.ny-poles-all"
        const val POLE_VECTOR_NY_LAYER_ID = "ny_poles"

        const val SPAN_VECTOR_MA_TILE_ID = "mapbox://aidash-ivms.ma_orig_v2"
        const val SPAN_VECTOR_MA_LAYER_ID = "ma_orig_v2"
        const val SPAN_VECTOR_NY_TILE_ID = "mapbox://aidash-ivms.ny_orig_v2"
        const val SPAN_VECTOR_NY_LAYER_ID = "ny_orig_v2"


        //For Specific to SVEC Client
        const val SPAN_VECTOR_FOR_SVEC_TILE_ID = "mapbox://aidash-ivms.0iq8k150"
        const val SPAN_VECTOR_FOR_SVEC_LAYER_ID = "conductor_svec-7nx7xx"
        const val POLE_VECTOR_FOR_SVEC_TILE_ID = "mapbox://aidash-ivms.7ew8iaml"
        const val POLE_VECTOR_FOR_SVEC_LAYER_ID = "poles_svec-0p1oeu"
        const val DEVICE_VECTOR_FOR_SVEC_TILE_ID = "mapbox://aidash-ivms.0qt373ud"
        const val DEVICE_VECTOR_FOR_SVEC_LAYER_ID = "oc_devices_svec-9dc1ks"

        //Old vector tile
        //const val DUKE_ENTITY_TILE_ID = "mapbox://aidash-ivms.961svz6n"
        //const val DUKE_ENTITY_TILE_LAYER_ID = "duke_shp_01-12-21-79fy1l"

        const val DUKE_ENTITY_TILE_ID = "mapbox://aidash-ivms.d6m49pgm"
        const val DUKE_ENTITY_TILE_LAYER_ID = "MOBILE_DUKE_Work_Unit_v7-1g09dl"

        const val DUKE_DEVICE_FUSE_TILE_ID = "mapbox://aidash-ivms.c8ybn18p"
        const val DUKE_DEVICE_FUSE_TILE_LAYER_ID = "DUKE_Mobile_fuse-3qmh8w"
        const val DUKE_DEVICE_RECLOSURE_TILE_ID = "mapbox://aidash-ivms.2ym7bdy0"
        const val DUKE_DEVICE_RECLOSURE_TILE_LAYER_ID = "DUKE_Mobile_reclosure-81gj3q"
        const val DUKE_SUBSTATION_TILE_ID = "mapbox://aidash-ivms.b61tdeyj"
        const val DUKE_SUBSTATION_TILE_LAYER_ID = "DUKE_Mobile_ss-0zdvmr"
    }
}

class CommentModuleType {
    companion object {
        const val HAZARD_TREE = 1
        const val CYCLE_TRIM = 2
        const val PLAN_ITEM = 3
        const val HOT_SPOT = 4
        const val FEEDER_RISK_ANALYSIS = 6
        const val SEGMENT_RISK_ANALYSIS = 7

        //const val SPAN_RISK_ANALYSIS = 8
        const val COMMON_TASK = 9
        const val RISK_EXECUTE_TASK = 12
    }
}


class RiskLevelBucketing {
    companion object {
        const val low = 1f
        const val medium = 2f
        const val high = 3f
    }
}

class FileName {
    companion object {
        const val treeMasterData = "TreeMasterData.json"
    }
}