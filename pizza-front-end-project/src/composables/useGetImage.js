export default function useGetImage() {
    function getImage(name) {
        return new URL(location.origin + "/src/assets/images/" + name)
    }

    return getImage 
}