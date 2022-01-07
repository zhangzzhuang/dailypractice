package com.example.sortagorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhangzhuang
 * @since 2021-04-06
 * 推荐结果打散
 */
public class RecommendResult {

	public List<String> getResult(List<String> picAndVideo, int maxInterval) {
		List<String> result = new ArrayList<>();
		if (picAndVideo == null || picAndVideo.size() == 0) {
			return result;
		}
		Queue<String> videoQueue = new LinkedList<>();
		Queue<String> picQueue = new LinkedList<>();
		boolean firstPic = false;
		int index = 0;
		int picAndVideoSize = picAndVideo.size();
		while (!firstPic && index < picAndVideoSize) {
			if (isVideo(picAndVideo.get(index))) {
				result.add(index, picAndVideo.get(index));
				index++;
			} else {
				firstPic = true;
			}
		}
		while (index < picAndVideoSize) {
			if (isVideo(picAndVideo.get(index))) {
				videoQueue.add(picAndVideo.get(index));
			} else {
				picQueue.add(picAndVideo.get(index));
			}
			index++;
		}
		int currentSize = result.size();
		while (!videoQueue.isEmpty() && !picQueue.isEmpty()) {
			if (currentSize >= maxInterval) {
				result.add(picQueue.poll());
				currentSize = 0;
			} else {
				result.add(videoQueue.poll());
				currentSize++;
			}
		}
		while (!videoQueue.isEmpty()) {
			result.add(videoQueue.poll());
		}
		if (currentSize >= maxInterval && !picQueue.isEmpty()) {
			result.add(picQueue.poll());
		}

		return result;
	}

	public boolean isVideo(String clip) {
		if (clip.indexOf("v") != -1) {
			return true;
		}
		return false;

	}

}
