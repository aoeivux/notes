# zsh
## plugins
### zsh-autosuggestions
  1. download into ~/.oh-my-zsh/custom/plugins/....

    git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions

  2. Add the plugin to the list of plugins for Oh My Zsh to load (inside ~/.zshrc)
    plugins=( 
    # other plugins...
    zsh-autosuggestions
)

### zsh-syntax-highlighting
  1. download into ~/.oh-my-zsh/custom/plugins/....

    git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting

  2. Add the pligin append in the zshrc
  plugin=(
  .....
  zsh-syntax-highlighting
  )

### zsh-completions

  1. download into ~/.oh-my-zsh/custom/plugins/...

    git clone https://github.com/zsh-users/zsh-completions ${ZSH_CUSTOM:-${ZSH:-~/.oh-my-zsh}/custom}/plugins/zsh-completions

  2. Append to the plugins
     plugin=(
    .....
    zsh-syntax-highlighting
    )



# alacritty themes selector

download with npm
```shell
  npm i - g alacritty-themes
```