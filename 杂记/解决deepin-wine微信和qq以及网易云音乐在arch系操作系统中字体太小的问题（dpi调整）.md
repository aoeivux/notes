# 微信

```shell
 WINEPREFIX=~/.deepinwine/Deepin-WeChat deepin-wine5 winecfg
```

# qq

```shell
  WINEPREFIX=~/.deepinwine/Deepin-QQ deepin-wine5 winecfg
```

# netease-cloud-music
修改最后一行

```shell
  exec "${HERE}"/netease-cloud-music --force-device-scale-factor=1.0 $@
```
数字可以根据大小自己设置


# OCR and 截图
ocr
yay -S corfeeder

引擎
yay -S tesseract-data-chi_sim

  1. 打开ocrfeeder工具，选择引擎，将引擎语言修改为只需要中文和英文zh:chi_sim,en:eng
OCR配置参考: https://www.jianshu.com/p/2d0e7c41ccee


截图使用kde自带的截图软件