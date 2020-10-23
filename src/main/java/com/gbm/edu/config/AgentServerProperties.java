package com.gbm.edu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * http://blog.saltfactory.net/java/load-yaml-file-in-spring.html
 */
@Data
@Component
@ConfigurationProperties(prefix="agent_set", ignoreUnknownFields=false)
public class AgentServerProperties {

	private String db_type;

	private String home_dir;

	private String file_out_dir;

	private String file_succ_dir;

	private int msg_select_large;

	private String service;

	private String version;

	private String client;

	private String id;

	private int large_fetch_expired_min;

	private String tcp_server;

	private int tcp_port;
}
