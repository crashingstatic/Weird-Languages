FROM node:20

ARG TZ
ENV TZ="$TZ"

ARG CLAUDE_CODE_VERSION=latest

# Install basic development tools and iptables/ipset
RUN apt-get update && apt-get install -y --no-install-recommends \
  less \
  git \
  procps \
  sudo \
  fzf \
  zsh \
  man-db \
  unzip \
  gnupg2 \
  gh \
  iptables \
  ipset \
  iproute2 \
  dnsutils \
  aggregate \
  jq \
  nano \
  vim \
  && apt-get clean && rm -rf /var/lib/apt/lists/*

# Ensure default node user has access to /usr/local/share
RUN mkdir -p /usr/local/share/npm-global && \
  chown -R node:node /usr/local/share

ARG USERNAME=node

# Persist bash history.
RUN SNIPPET="export PROMPT_COMMAND='history -a' && export HISTFILE=/commandhistory/.bash_history" \
  && mkdir /commandhistory \
  && touch /commandhistory/.bash_history \
  && chown -R $USERNAME /commandhistory

# Set `DEVCONTAINER` environment variable to help with orientation
ENV DEVCONTAINER=true

# Create workspace and config directories and set permissions
RUN mkdir -p /workspace /home/node/.claude && \
  chown -R node:node /workspace /home/node/.claude

WORKDIR /workspace

ARG GIT_DELTA_VERSION=0.18.2
RUN ARCH=$(dpkg --print-architecture) && \
  wget "https://github.com/dandavison/delta/releases/download/${GIT_DELTA_VERSION}/git-delta_${GIT_DELTA_VERSION}_${ARCH}.deb" && \
  sudo dpkg -i "git-delta_${GIT_DELTA_VERSION}_${ARCH}.deb" && \
  rm "git-delta_${GIT_DELTA_VERSION}_${ARCH}.deb"

# Set up non-root user
USER node

# Install global packages
ENV NPM_CONFIG_PREFIX=/usr/local/share/npm-global
ENV PATH=$PATH:/usr/local/share/npm-global/bin

# Set the default shell to zsh rather than sh
ENV SHELL=/bin/zsh

# Set the default editor and visual
ENV EDITOR=nano
ENV VISUAL=nano

# Default powerline10k theme
ARG ZSH_IN_DOCKER_VERSION=1.2.0
RUN sh -c "$(wget -O- https://github.com/deluan/zsh-in-docker/releases/download/v${ZSH_IN_DOCKER_VERSION}/zsh-in-docker.sh)" -- \
  -p git \
  -p fzf \
  -a "source /usr/share/doc/fzf/examples/key-bindings.zsh" \
  -a "source /usr/share/doc/fzf/examples/completion.zsh" \
  -a "export PROMPT_COMMAND='history -a' && export HISTFILE=/commandhistory/.bash_history" \
  -x

# Install Claude
RUN npm install -g @anthropic-ai/claude-code@${CLAUDE_CODE_VERSION}

# Copy and set up firewall script
COPY init-firewall.sh /usr/local/bin/
USER root
RUN chmod +x /usr/local/bin/init-firewall.sh && \
  echo "node ALL=(root) NOPASSWD: /usr/local/bin/init-firewall.sh" > /etc/sudoers.d/node-firewall && \
  chmod 0440 /etc/sudoers.d/node-firewall
USER node

# APT should be passwordless
USER root
RUN echo "node ALL=(root) NOPASSWD: /usr/bin/apt-get, /usr/bin/apt" > /etc/sudoers.d/node-apt && \
  chmod 0440 /etc/sudoers.d/node-apt
USER node

USER root
# --- Clojure tooling ---
# JDK (OpenJDK 17 from Debian repos) + rlwrap
RUN apt-get update && apt-get install -y --no-install-recommends \
  default-jdk \
  rlwrap \
  && apt-get clean && rm -rf /var/lib/apt/lists/*

# Clojure CLI (tools.deps)
ARG CLOJURE_VERSION=1.12.0.1530
RUN curl -fsSL "https://download.clojure.org/install/linux-install-${CLOJURE_VERSION}.sh" \
  -o /tmp/clojure-install.sh \
  && chmod +x /tmp/clojure-install.sh \
  && /tmp/clojure-install.sh \
  && rm /tmp/clojure-install.sh

# clj-kondo linter
ARG CLJ_KONDO_VERSION=2024.11.14
RUN ARCH=$(dpkg --print-architecture) \
  && curl -fsSL "https://github.com/clj-kondo/clj-kondo/releases/download/v${CLJ_KONDO_VERSION}/clj-kondo-${CLJ_KONDO_VERSION}-linux-${ARCH}.zip" \
     -o /tmp/clj-kondo.zip \
  && unzip -o /tmp/clj-kondo.zip -d /usr/local/bin \
  && rm /tmp/clj-kondo.zip \
  && chmod +x /usr/local/bin/clj-kondo
USER node