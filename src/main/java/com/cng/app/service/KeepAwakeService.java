package com.cng.app.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * KeepAwakeService - DISABLED
 * 
 * Previously pinged the app every 10 minutes to prevent Render free-tier sleeping.
 * This consumed 744 hours/month (24h × 31 days), exhausting the entire 750-hour
 * free quota on Render.
 * 
 * REMOVED to prevent billing overrun. Accept ~30s cold-start on first visit instead.
 */
@Service
@EnableScheduling
public class KeepAwakeService {
    // Intentionally empty — keep-awake pings removed to conserve hosting hours.
}
