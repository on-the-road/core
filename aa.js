function getTimes(fileName) {

    var url = URL.createObjectURL(fileName);
     //经测试，发现audio也可获取视频的时长
    var audioElement = new Audio(url);

    var duration;
    audioElement.addEventListener("loadedmetadata", function (_event) {
        duration = audioElement.duration;
        return duration
    });
}