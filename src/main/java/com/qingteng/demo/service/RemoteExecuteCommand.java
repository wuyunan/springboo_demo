package com.qingteng.demo.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * @ClassName: RemoteExecuteCommand
 * @Description: 远程执行Linux命令
 * @date: 2017年10月9日 下午5:44:42
 */
public class RemoteExecuteCommand {

    public static String executeRemoteCommand(
            String username,
            String password,
            String hostname,
            int port) throws Exception {

        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname, 22);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);

        session.connect();

        // SSH Channel
        ChannelExec channelssh = (ChannelExec) session.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        // Execute command
        channelssh.setCommand("ls");
        channelssh.connect();
        channelssh.disconnect();

        return baos.toString();
    }
}