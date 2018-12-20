#!/usr/bin/env bash
ffmpeg -i input.mp4 -c:v h264 -f rtsp -strict -2 rtsp://127.0.0.1:8554/stream.mp4