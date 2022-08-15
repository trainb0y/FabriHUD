Each element provides formatting placeholders, which can be inserted by substituting [java formatting placeholders](https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html) in the text of the override.   
Coloring is done using [legacy color formatting](https://minecraft.fandom.com/wiki/Formatting_codes) though due to a SpruceUI limitation, the input box cursor may behave strangely when using color codes.  
### Override Example  
| Element    | Avalible   | Default Content | Example Override           |
| ---------- | ---------- | --------------- |----------------------------|
| Biome      | biome name | Biome: %s       | §7Current Biome: §a%s      |
| FPS        | fps        | FPS: %d         | §7Frames Per Second §l§n%d |
| Latency    | latency ms | Ping: %dms      | §7Pong: §n%d milliseconds  |
| Position   | x, y, z    | (%d, %d, %d)    | §7[§9%d§7, §b%d§7, §5%d§7] |
| Time       | hour, min  | Time: %1d:%1d   | §7Currently §6%d§7 o'clock |

#### Original HUD (Defaults): 
![image](https://user-images.githubusercontent.com/66213737/184697470-87229185-82f0-4bd6-bd3d-4c3b6562e1fb.png)  
#### Overridden: 
![image](https://user-images.githubusercontent.com/66213737/184697401-1b4a6fb1-4e06-4e64-a4a4-5ab08bb05b9a.png)