import yt_dlp
import json

def get_video_info(url):
    """
    Fetches video information using yt-dlp.
    Returns a JSON string with title, thumbnail, duration, formats, etc.
    """
    ydl_opts = {
        'quiet': True,
        'no_warnings': True,
        'dump_single_json': True,
        'skip_download': True,
    }
    try:
        with yt_dlp.YoutubeDL(ydl_opts) as ydl:
            info_dict = ydl.extract_info(url, download=False)
            return json.dumps(info_dict)
    except Exception as e:
        # It's good practice to return error information
        error_info = {
            "error": True,
            "message": str(e)
        }
        return json.dumps(error_info)
