package com.DTUHackathon.Executive4.O.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@RestController
@RequestMapping("/api/video")
@CrossOrigin(origins = {"http://localhost:5173", "https://rentrite-live.vercel.app"})
public class VideoCallController {

    @GetMapping("/generate-meeting")
    public ResponseEntity<Map<String, String>> generateMeetingLink() {
        String meetingId = UUID.randomUUID().toString();
        String meetingURL = "https://meet.jit.si/" + meetingId;

        Map<String, String> response = new HashMap<>();
        response.put("meetingURL", meetingURL);

        return ResponseEntity.ok(response);
    }
}