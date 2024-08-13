from gtts import gTTS
import pygame
import time
import os
import speech_recognition as sr

def recommend(text):
    if "쎈 칵테일" in text or "센 칵테일" in text:
        return "마가리타"
    elif "약한 칵테일" in text:
        return "프로즌 미고"
    else:
        return "잘 모르겠습니다."

def recommend_song(mood):
    if "신나는 노래" in mood:
        return "그쪽도 홍박사님을 아세요?"
    elif "잔잔한 노래" in mood:
        return "잔잔한 노래입니다."
    else:
        return "잘 모르겠습니다."

def recognize_speech():
    recognizer = sr.Recognizer()

    with sr.Microphone() as source:
        print("말씀해주세요...")
        recognizer.adjust_for_ambient_noise(source)  # 환경 소음 조절
        audio = recognizer.listen(source, timeout=20)

    try:
        print("음성 인식 중...")
        text = recognizer.recognize_google(audio, language="ko-KR")
        return text
    except sr.UnknownValueError:
        print("음성을 인식할 수 없습니다.")
        return None
    except sr.RequestError as e:
        print(f"음성 인식 서비스에 오류가 있습니다: {e}")
        return None

def recognize_speech1():
    recognizer = sr.Recognizer()

    with sr.Microphone() as source:
        print("말씀해주세요...")
        recognizer.adjust_for_ambient_noise(source)  # 환경 소음 조절
        audio = recognizer.listen(source)

    try:
        print("음성 인식 중...")
        text = recognizer.recognize_google(audio, language="ko-KR")
        return text
    except sr.UnknownValueError:
        print("음성을 인식할 수 없습니다.")
        return None
    except sr.RequestError as e:
        print(f"음성 인식 서비스에 오류가 있습니다: {e}")
        return None

def speak_text_gtts(text):
    pygame.init()  # pygame 초기화
    tts = gTTS(text=text, lang='ko', slow=False)
    tts.save("output.mp3")
    pygame.mixer.music.load("output.mp3")
    pygame.mixer.music.play()

    while pygame.mixer.music.get_busy():
        pygame.time.Clock().tick(10)

def play_music(file_path):
    pygame.mixer.music.load(file_path)
    pygame.mixer.music.play()

# main 함수 내에서 check_call 함수 호출 전 user_input 초기화 및 None 처리
def main():
    user_input = ""

    # 호출어 확인 함수
    def check_call(text):
        return text and "안녕 연암" in text

    # 호출어가 입력될 때까지 반복
    while not check_call(user_input):
        # 음성 인식 함수 호출
        user_input = recognize_speech1()
        print(user_input)
        if check_call(user_input):  # 호출어가 아니면 메시지 출력
            speak_text_gtts("안녕하세요 주인님. 칵테일 추천을 원하시면 '쎈 칵테일' 또는 약한 칵테일을 말씀하세요: ")
        else:
            speak_text_gtts("제 이름을 까먹으셨나요...")

    # 칵테일 선택 받기
    while True:
        # 음성 인식 함수 호출
        user_input = recognize_speech()

        user_input = user_input.replace("쎈", "센")

        # 함수 호출 및 결과 출력
        recommend_result = recommend(user_input)
        print(recommend_result)
        speak_text_gtts(recommend_result)  # 추천 음성 출력

        # 입력값이 올바른지 확인
        if "센 칵테일" in user_input or "약한 칵테일" in user_input:
            print(user_input)
            break
        else:
            print(user_input)
            speak_text_gtts("다시 얘기해주세요")

    # 호출어가 일치하면 출력
    speak_text_gtts("노래분위기를 골라주세요. 신나는 노래와 잔잔한 노래가 있습니다.")
    # 노래 선택 받기
    while True:
        # 음성 인식 함수 호출
        mood_input = recognize_speech()
        # 선택에 따라 처리
        if "신나는 노래" in mood_input or "잔잔한 노래" in mood_input:
            recommend_result = recommend_song(mood_input)
            print(recommend_result)
            speak_text_gtts(recommend_result)  # 추천 음성 출력
        if "신나는 노래" in mood_input:
            play_music("wow.mp3")
            while pygame.mixer.music.get_busy():  # 노래가 재생 중일 동안 대기
                continue
            break
        elif "잔잔한 노래" in mood_input:
            play_music("soso.mp3")
            while pygame.mixer.music.get_busy():  # 노래가 재생 중일 동안 대기
                continue
            break
        else:
            speak_text_gtts("잘 모르겠습니다. '신나는 노래' 또는 '잔잔한 노래' 중 하나를 말씀해주세요.")



# 프로그램 실행
while True:
    main()
