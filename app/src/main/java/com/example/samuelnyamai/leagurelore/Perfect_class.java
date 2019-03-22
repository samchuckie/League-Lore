/*
package com.example.samuelnyamai.leagurelore;

public class Perfect_class {
-----------------------------------com.example.Datum.java-----------------------------------

package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("version")
@Expose
private String version;
@SerializedName("id")
@Expose
private String id;
@SerializedName("key")
@Expose
private String key;
@SerializedName("name")
@Expose
private String name;
@SerializedName("title")
@Expose
private String title;
@SerializedName("blurb")
@Expose
private String blurb;
@SerializedName("info")
@Expose
private Info info;
@SerializedName("image")
@Expose
private Image image;
@SerializedName("tags")
@Expose
private List<String> tags = null;
@SerializedName("partype")
@Expose
private String partype;
@SerializedName("stats")
@Expose
private Stats stats;

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getKey() {
return key;
}

public void setKey(String key) {
this.key = key;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getBlurb() {
return blurb;
}

public void setBlurb(String blurb) {
this.blurb = blurb;
}

public Info getInfo() {
return info;
}

public void setInfo(Info info) {
this.info = info;
}

public Image getImage() {
return image;
}

public void setImage(Image image) {
this.image = image;
}

public List<String> getTags() {
return tags;
}

public void setTags(List<String> tags) {
this.tags = tags;
}

public String getPartype() {
return partype;
}

public void setPartype(String partype) {
this.partype = partype;
}

public Stats getStats() {
return stats;
}

public void setStats(Stats stats) {
this.stats = stats;
}

}
-----------------------------------com.example.Example.java-----------------------------------

package com.example;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

@SerializedName("type")
@Expose
private String type;
@SerializedName("format")
@Expose
private String format;
@SerializedName("version")
@Expose
private String version;
@SerializedName("data")
@Expose
private List<Datum> data = null;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getFormat() {
return format;
}

public void setFormat(String format) {
this.format = format;
}

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

}
-----------------------------------com.example.Image.java-----------------------------------

package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

@SerializedName("full")
@Expose
private String full;
@SerializedName("sprite")
@Expose
private String sprite;
@SerializedName("group")
@Expose
private String group;
@SerializedName("x")
@Expose
private Integer x;
@SerializedName("y")
@Expose
private Integer y;
@SerializedName("w")
@Expose
private Integer w;
@SerializedName("h")
@Expose
private Integer h;

public String getFull() {
return full;
}

public void setFull(String full) {
this.full = full;
}

public String getSprite() {
return sprite;
}

public void setSprite(String sprite) {
this.sprite = sprite;
}

public String getGroup() {
return group;
}

public void setGroup(String group) {
this.group = group;
}

public Integer getX() {
return x;
}

public void setX(Integer x) {
this.x = x;
}

public Integer getY() {
return y;
}

public void setY(Integer y) {
this.y = y;
}

public Integer getW() {
return w;
}

public void setW(Integer w) {
this.w = w;
}

public Integer getH() {
return h;
}

public void setH(Integer h) {
this.h = h;
}

}
-----------------------------------com.example.Info.java-----------------------------------

package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

@SerializedName("attack")
@Expose
private Integer attack;
@SerializedName("defense")
@Expose
private Integer defense;
@SerializedName("magic")
@Expose
private Integer magic;
@SerializedName("difficulty")
@Expose
private Integer difficulty;

public Integer getAttack() {
return attack;
}

public void setAttack(Integer attack) {
this.attack = attack;
}

public Integer getDefense() {
return defense;
}

public void setDefense(Integer defense) {
this.defense = defense;
}

public Integer getMagic() {
return magic;
}

public void setMagic(Integer magic) {
this.magic = magic;
}

public Integer getDifficulty() {
return difficulty;
}

public void setDifficulty(Integer difficulty) {
this.difficulty = difficulty;
}

}
-----------------------------------com.example.Stats.java-----------------------------------

package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

@SerializedName("hp")
@Expose
private Double hp;
@SerializedName("hpperlevel")
@Expose
private Integer hpperlevel;
@SerializedName("mp")
@Expose
private Double mp;
@SerializedName("mpperlevel")
@Expose
private Integer mpperlevel;
@SerializedName("movespeed")
@Expose
private Integer movespeed;
@SerializedName("armor")
@Expose
private Double armor;
@SerializedName("armorperlevel")
@Expose
private Double armorperlevel;
@SerializedName("spellblock")
@Expose
private Double spellblock;
@SerializedName("spellblockperlevel")
@Expose
private Double spellblockperlevel;
@SerializedName("attackrange")
@Expose
private Integer attackrange;
@SerializedName("hpregen")
@Expose
private Double hpregen;
@SerializedName("hpregenperlevel")
@Expose
private Double hpregenperlevel;
@SerializedName("mpregen")
@Expose
private Integer mpregen;
@SerializedName("mpregenperlevel")
@Expose
private Integer mpregenperlevel;
@SerializedName("crit")
@Expose
private Integer crit;
@SerializedName("critperlevel")
@Expose
private Integer critperlevel;
@SerializedName("attackdamage")
@Expose
private Double attackdamage;
@SerializedName("attackdamageperlevel")
@Expose
private Double attackdamageperlevel;
@SerializedName("attackspeedoffset")
@Expose
private Double attackspeedoffset;
@SerializedName("attackspeedperlevel")
@Expose
private Integer attackspeedperlevel;

public Double getHp() {
return hp;
}

public void setHp(Double hp) {
this.hp = hp;
}

public Integer getHpperlevel() {
return hpperlevel;
}

public void setHpperlevel(Integer hpperlevel) {
this.hpperlevel = hpperlevel;
}

public Double getMp() {
return mp;
}

public void setMp(Double mp) {
this.mp = mp;
}

public Integer getMpperlevel() {
return mpperlevel;
}

public void setMpperlevel(Integer mpperlevel) {
this.mpperlevel = mpperlevel;
}

public Integer getMovespeed() {
return movespeed;
}

public void setMovespeed(Integer movespeed) {
this.movespeed = movespeed;
}

public Double getArmor() {
return armor;
}

public void setArmor(Double armor) {
this.armor = armor;
}

public Double getArmorperlevel() {
return armorperlevel;
}

public void setArmorperlevel(Double armorperlevel) {
this.armorperlevel = armorperlevel;
}

public Double getSpellblock() {
return spellblock;
}

public void setSpellblock(Double spellblock) {
this.spellblock = spellblock;
}

public Double getSpellblockperlevel() {
return spellblockperlevel;
}

public void setSpellblockperlevel(Double spellblockperlevel) {
this.spellblockperlevel = spellblockperlevel;
}

public Integer getAttackrange() {
return attackrange;
}

public void setAttackrange(Integer attackrange) {
this.attackrange = attackrange;
}

public Double getHpregen() {
return hpregen;
}

public void setHpregen(Double hpregen) {
this.hpregen = hpregen;
}

public Double getHpregenperlevel() {
return hpregenperlevel;
}

public void setHpregenperlevel(Double hpregenperlevel) {
this.hpregenperlevel = hpregenperlevel;
}

public Integer getMpregen() {
return mpregen;
}

public void setMpregen(Integer mpregen) {
this.mpregen = mpregen;
}

public Integer getMpregenperlevel() {
return mpregenperlevel;
}

public void setMpregenperlevel(Integer mpregenperlevel) {
this.mpregenperlevel = mpregenperlevel;
}

public Integer getCrit() {
return crit;
}

public void setCrit(Integer crit) {
this.crit = crit;
}

public Integer getCritperlevel() {
return critperlevel;
}

public void setCritperlevel(Integer critperlevel) {
this.critperlevel = critperlevel;
}

public Double getAttackdamage() {
return attackdamage;
}

public void setAttackdamage(Double attackdamage) {
this.attackdamage = attackdamage;
}

public Double getAttackdamageperlevel() {
return attackdamageperlevel;
}

public void setAttackdamageperlevel(Double attackdamageperlevel) {
this.attackdamageperlevel = attackdamageperlevel;
}

public Double getAttackspeedoffset() {
return attackspeedoffset;
}

public void setAttackspeedoffset(Double attackspeedoffset) {
this.attackspeedoffset = attackspeedoffset;
}

public Integer getAttackspeedperlevel() {
return attackspeedperlevel;
}

public void setAttackspeedperlevel(Integer attackspeedperlevel) {
this.attackspeedperlevel = attackspeedperlevel;
}

}

}
*/
