package cn.zhouyafeng.itchat4j.demo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.DownloadTools;
import cn.zhouyafeng.itchat4j.utils.MsgType;

/**
 * 简单示例程序，收到文本信息自动回复原信息，收到图片、语音、小视频后根据路径自动保存
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年4月25日 上午12:18:09
 * @version 1.0
 *
 */
public class SimpleDemo implements IMsgHandlerFace {

	@Override
	public String textMsgHandle(JSONObject msg) {
		String docFilePath = "D:/itchat4j/pic/test.docx";
		String pngFilePath = "D:/itchat4j/pic/test.png";
		String pdfFilePath = "D:/itchat4j/pic/测试.pdf";
		String txtFilePath = "D:/itchat4j/pic/test.txt";
		MessageTools.sendFileMsgByNickName("yaphone", docFilePath);
		MessageTools.sendFileMsgByNickName("yaphone", pngFilePath);
		MessageTools.sendFileMsgByNickName("yaphone", pdfFilePath);
		MessageTools.sendFileMsgByNickName("yaphone", txtFilePath);
		// String text = msg.getString("Text");
		// return text;
		return null;
	}

	@Override
	public String picMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String picPath = "D://itchat4j/pic" + File.separator + fileName + ".jpg";
		DownloadTools.getDownloadFn(msg, MsgType.PIC, picPath);
		return "图片保存成功";
	}

	@Override
	public String voiceMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String voicePath = "D://itchat4j/voice" + File.separator + fileName + ".mp3";
		DownloadTools.getDownloadFn(msg, MsgType.VOICE, voicePath);
		return "声音保存成功";
	}

	@Override
	public String viedoMsgHandle(JSONObject msg) {
		System.out.println(msg);
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String viedoPath = "D://itchat4j/viedo" + File.separator + fileName + ".mp4";
		DownloadTools.getDownloadFn(msg, MsgType.VIEDO, viedoPath);
		return "视频保存成功";
	}

	@Override
	public String nameCardMsgHandle(JSONObject msg) {
		return "收到名片消息";
	}

	public static void main(String[] args) {
		Wechat wechat = new Wechat("~/Downloads");
		wechat.start();
	}

}
