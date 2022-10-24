# Download scoop 
```shell
irm get.scoop.sh | iex
```

# Download lazygit 

```shell
scoop bucket add extras 
scoop install lazygit
```

# Download Modules

```shell
Install-Module -Name Terminal-Icons -Repository PSGallery -Force
Install-Module -Name z -Force
Install-Module -Name PSReadLine -AllowPrerelease -Scope CurrentUser -Force -SkipPublishercheck
Install-Module -Name PSFzf -Scope CurrentUser -Force
```

# Download oh-my-zsh

```shell
Set-ExecutionPolicy Bypass -Scope Process -Force; Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://ohmyposh.dev/install.ps1'))
```