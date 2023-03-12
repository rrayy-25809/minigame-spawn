# minigame-spawn

대부분의 미니게임에서 사용하는 팀 스폰, 딜레이 스폰 같은 기능을 구현시켜주는 플러그인 입니다.

This plugin can use the team spawn and delayed spawn functions that are used in almost minigames.

## how to use it as plugin

이 플러그인은 크게 두 가지 기능이 있습니다, 바로 팀 스폰과 딜레이 스폰입니다.

해당 플러그인은 config.yml 을 수정함으로써 두 기능을 키고 끌 수 있습니다.

this plugin can turn on and turn off functions as edit config.yml file

config.yml
```yml
team-respawn: true
delayed-respawn: true
respawn-delay: 10 #second
basic-team: true
```

config.yml에서 ```team-respawn```을 ```true```로 설정 하셨다면 같은 팀인 모든 플레이어가 같은 리스폰 위치를 공유합니다.

리스폰 위치는 /teamspawn 명령어를 통해 설정 가능합니다.

config.yml에서 ```delayed-respawn```을 ```true```로 설정 하셨다면 특정 초 후에 리스폰 합니다.

리스폰 딜레이 기능은 config.yml에서 ```respawn-delay```을 수정하시거나 /delay 명령어를 통해 설정 가능합니다.

## how to use it as library

해당 플러그인은 라이브러리로 불러와서 기능을 추가하고 응용하는 것이 가능합니다.

This plugin can add functions

### add library
gradle
```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
    
dependencies {
  implementation 'com.github.rrayy-25809:minigame-spawn:Tag'
}
```

<!--meven
```
print("meven")
```

### get plugin

### example code()--!>
