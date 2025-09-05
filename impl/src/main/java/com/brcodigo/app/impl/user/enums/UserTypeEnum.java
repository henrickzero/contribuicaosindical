package com.brcodigo.app.impl.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum UserTypeEnum {
    USER(Arrays.asList(
            "/api/v1/user/subsidiaries",
            "/api/v1/youtube/create",
            "/api/v1/youtube/channel/.*/playlist",
            "/api/v1/youtube/channel/.*/search",
            "/api/v1/youtube/channel/.*/comments",
            "/api/v1/youtube/channel/.*/comments/channel",
            "/api/v1/youtube/channel/.*/comments/video/.*",
            "/api/v1/youtube/channel/.*/comments/insert",
            "/api/v1/util",
            "/api/v1/pix/generate",
            "/api/v1/channel/.*/campaign/create",
            "/api/v1/channel/.*/campaign/video/create",
            "/api/v1/channel/.*/campaign/process",
            "/api/v1/channel/.*/autoreplay/create",
            "/api/v1/channel/.*/autoreplay/process",
            "/api/v1/chatgpt/completions",
            "/api/v1/items",
            "/api/v1/orders",
            "/api/v1/clients",
            "/api/v1/clients/.*",
            "/api/v1/clients/filter",
            "/api/v1/clients/last-ten"
    )),
    COMPANY(Arrays.asList(
            "/api/v1/user/subsidiaries",
            "/api/v1/youtube/create",
            "/api/v1/youtube/channel/.*/playlist",
            "/api/v1/youtube/channel/.*/search",
            "/api/v1/youtube/channel/.*/comments",
            "/api/v1/youtube/channel/.*/comments/channel",
            "/api/v1/youtube/channel/.*/comments/video/.*",
            "/api/v1/youtube/channel/.*/comments/insert",
            "/api/v1/util",
            "/api/v1/pix/generate",
            "/api/v1/channel/.*/campaign/create",
            "/api/v1/channel/.*/campaign/video/create",
            "/api/v1/channel/.*/campaign/process",
            "/api/v1/channel/.*/autoreplay/create",
            "/api/v1/channel/.*/autoreplay/process",
            "/api/v1/chatgpt/completions",
            "/api/v1/items",
            "/api/v1/orders",
            "/api/v1/clients",
            "/api/v1/clients/.*",
            "/api/v1/clients/filter",
            "/api/v1/clients/last-ten"
    )),
    SUBSIDIARY(Arrays.asList(
            "/api/v1/user/subsidiaries",
            "/api/v1/youtube/create",
            "/api/v1/youtube/channel/.*/playlist",
            "/api/v1/youtube/channel/.*/search",
            "/api/v1/youtube/channel/.*/comments",
            "/api/v1/youtube/channel/.*/comments/channel",
            "/api/v1/youtube/channel/.*/comments/video/.*",
            "/api/v1/youtube/channel/.*/comments/insert",
            "/api/v1/util",
            "/api/v1/pix/generate",
            "/api/v1/channel/.*/campaign/create",
            "/api/v1/channel/.*/campaign/video/create",
            "/api/v1/channel/.*/campaign/process",
            "/api/v1/channel/.*/autoreplay/create",
            "/api/v1/channel/.*/autoreplay/process",
            "/api/v1/chatgpt/completions",
            "/api/v1/items",
            "/api/v1/orders",
            "/api/v1/clients",
            "/api/v1/clients/.*",
            "/api/v1/clients/filter",
            "/api/v1/clients/last-ten"
    )),
    ADMIN(Arrays.asList(
            "/api/v1/user/subsidiaries",
            "/api/v1/youtube/create",
            "/api/v1/youtube/channel/.*/playlist",
            "/api/v1/youtube/channel/.*/search",
            "/api/v1/youtube/channel/.*/comments",
            "/api/v1/youtube/channel/.*/comments/channel",
            "/api/v1/youtube/channel/.*/comments/video/.*",
            "/api/v1/youtube/channel/.*/comments/insert",
            "/api/v1/util",
            "/api/v1/pix/generate",
            "/api/v1/channel/.*/campaign/create",
            "/api/v1/channel/.*/campaign/video/create",
            "/api/v1/channel/.*/campaign/process",
            "/api/v1/channel/.*/autoreplay/create",
            "/api/v1/channel/.*/autoreplay/process",
            "/api/v1/chatgpt/completions",
            "/api/v1/items",
            "/api/v1/orders",
            "/api/v1/clients",
            "/api/v1/clients/.*",
            "/api/v1/clients/filter",
            "/api/v1/clients/last-ten"
    ));

    private final List<String> rules;

}
