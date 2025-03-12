package com.ruoxijun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    // 1xx 信息
    CONTINUE(100, "Continue", "继续"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols", "切换协议"),
    PROCESSING(102, "Processing", "处理中"),
    // 2xx 成功
    SUCCESS(200, "Success", "成功"),
    CREATED(201, "Created", "已创建"),
    ACCEPTED(202, "Accepted", "已接受"),
    NON_AUTHORITATIVE_INFO(203, "Non-Authoritative Information", "非权威信息"),
    NO_CONTENT(204, "No Content", "无内容"),
    RESET_CONTENT(205, "Reset Content", "重置内容"),
    PARTIAL_CONTENT(206, "Partial Content", "部分内容"),
    MULTI_STATUS(207, "Multi-Status", "多状态"),
    // 3xx 重定向
    MULTIPLE_CHOICES(300, "Multiple Choices", "多种选择"),
    MOVED_PERMANENTLY(301, "Moved Permanently", "永久移动"),
    FOUND(302, "Found", "临时移动"),
    SEE_OTHER(303, "See Other", "查看其他位置"),
    NOT_MODIFIED(304, "Not Modified", "未修改"),
    USE_PROXY(305, "Use Proxy", "使用代理"),
    UNUSED(306, "Unused", "未使用"), // 已废弃，保留历史意义。
    TEMP_REDIRECT(307, "Temporary Redirect", "临时重定向"),
    // 4xx 客户端错误
    BAD_REQUEST(400, "Bad Request", "错误请求"),
    UNAUTHORIZED(401, "Unauthorized", "未授权"),
    PAYMENT_REQUIRED(402, "Payment Required", "需要付费"), // 不常用。
    FORBIDDEN(403, "Forbidden", "禁止"),
    NOT_FOUND(404, "Not Found", "未找到"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "方法不允许"),
    NOT_ACCEPTABLE(406, "Not Acceptable", "不接受"),
    PROXY_AUTH_REQUIRED(407, "Proxy Authentication Required", "需要代理认证"),
    REQUEST_TIMEOUT(408, "Request Timeout", "请求超时"),
    CONFLICT(409, "Conflict", "冲突"),
    GONE(410, "Gone", "已删除"),
    LENGTH_REQUIRED(411, "Length Required", "需要长度"),
    PRECON_FAILED(412, "Precondition Failed", "前提条件失败"),
    ENTITY_TOO_LARGE(413, "Request Entity Too Large", "请求实体过大"),
    URI_TOO_LONG(414, "Request-URI Too Long", "请求URI过长"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type", "不支持的媒体类型"),
    RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable", "请求范围不符合"),
    EXPECTATION_FAILED(417, "Expectation Failed", "期望失败"),
    TEAPOT(418, "I'm a teapot", "我是一个茶壶"), // 开玩笑的状态码。
    MISDIRECTED_REQUEST(421, "Misdirected Request", "错误的请求"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity", "无法处理的实体"),
    LOCKED(423, "Locked", "锁定"),
    FAILED_DEPENDENCY(424, "Failed Dependency", "依赖失败"),
    TOO_EARLY(425, "Too Early", "太早"),
    UPGRADE_REQUIRED(426, "Upgrade Required", "需要升级"),
    RETRY_WITH(449, "Retry With", "请重试"), // 微软扩展。
    LEGAL_REASONS(451, "Unavailable For Legal Reasons", "因法律原因不可用"),
    // 5xx 服务器错误
    FAIL(500, "Internal Server Error", "内部服务器错误"),
    NOT_IMPLEMENTED(501, "Not Implemented", "未实现"),
    BAD_GATEWAY(502, "Bad Gateway", "错误网关"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable", "服务不可用"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout", "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported", "HTTP版本不支持"),
    VARIANT_NEGOTIATES(506, "Variant Also Negotiates", "变体协商"),
    INSUFFICIENT_STORAGE(507, "Insufficient Storage", "存储不足"),
    BANDWIDTH_EXCEEDED(509, "Bandwidth Limit Exceeded", "带宽超限"),
    NOT_EXTENDED(510, "Not Extended", "未扩展"),
    UNPARSEABLE_HEADERS(600, "Unparseable Response Headers", "无法解析的响应头"); // 非标准。

    private final Integer code;
    private final String msg;
    private final String description;
}