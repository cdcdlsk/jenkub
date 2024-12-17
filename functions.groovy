def initHelm() {
    sh '''
        echo "Installing Helm CLI"
        helm version || curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
    '''
}

def deployWithHelm() {
    sh '''
        echo "Deploying with Helm"
        helm repo add stable https://charts.helm.sh/stable
        helm install my-release stable/nginx-ingress
    '''
}

return this
